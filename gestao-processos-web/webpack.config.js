var resolve = require('path').resolve;
var app_dir = resolve(__dirname, "app");

module.exports = {
    entry: app_dir + '/index.js',
    devtool: 'source-map',
    output: {
        filename: 'index.bundle.js',
        path: app_dir
    }
};