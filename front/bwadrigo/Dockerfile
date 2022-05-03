# node 14버전으로 이미지 생성
FROM node:14 as build-stage 
# 작업 디렉토리
WORKDIR var/jenkins_home/workspace/fessaffron/front/bwadrigo

# package.json,파일을 컨테이너 작업공간에 복사
COPY package*.json ./
# 의존성 설치
RUN npm install
# 코드전체 컨테이너로 복사
COPY . .
# dist파일 생성
RUN npm run build

#nginx 위에 만듬
FROM nginx:stable-alpine as production-stage
# 만들어진파일 디렉토리로 복사
COPY --from=build-stage ./dist /usr/share/nginx/html
EXPOSE 80

# nginx 백그라운드 실행
CMD ["nginx", "-g","daemon off;"]