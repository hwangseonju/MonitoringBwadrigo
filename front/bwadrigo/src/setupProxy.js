const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware('/v1/api',{
      target: 'http://k6s104.p.ssafy.io:8081',
      changeOrigin: true
    })
  );
};