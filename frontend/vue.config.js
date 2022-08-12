const { defineConfig } = require("@vue/cli-service");
const path = require("path");

module.exports = defineConfig({
  chainWebpack: (config) => {
    config.resolve.alias.set(
      "@components",
      path.resolve(__dirname, "src/components/")
    );
    config.resolve.alias.set("@type", path.resolve(__dirname, "type/"));
  },
  transpileDependencies: true,
});
