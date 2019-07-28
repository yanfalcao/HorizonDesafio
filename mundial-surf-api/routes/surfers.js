module.exports = app => {
    const Surfistas = app.db.models.Surfers;
    app.get("/surfistas", (req, res) => {
        Surfistas.findAll({}).then(Surfistas => {
            res.json({surfistas: surfistas});
        });
    });
};