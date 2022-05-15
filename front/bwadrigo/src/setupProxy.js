const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware('/v1/api',{
      target: 'http://k6s1041.p.ssafy.io:8083',
      changeOrigin: true
    })
  );
};
