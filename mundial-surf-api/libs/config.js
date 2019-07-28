module.exports = {
  database: "mundialsurf",
  username: "",
  password: "",
  params: {
    dialect: "sqlite",
    storage: "mundialsurf.sqlite",
    logging: false,
    sync: {
      force: true
    },
    define: {
      underscored: true
    }
  }
};