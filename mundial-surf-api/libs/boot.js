module.exports = app => {
    app.db.sequelize.sync().done(() => {
        app.listen(app.get("port"), () => {
        console.log('Mundial Surf API - porta ' + app.get("port"));
        });
    });   
}