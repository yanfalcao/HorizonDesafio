module.exports = (sequelize, DataType) => {
    const Baterias = sequelize.define("Baterias", {
        id: {
            type: DataType.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        surfista1: {
            type: DataType.INTEGER,
            allowNull: false,
            validate: {
                notEmpty: true
            }
        },
        surfista2: {
            type: DataType.INTEGER,
            allowNull: false,
            validate: {
                notEmpty: true
            }
        }
    },  {
        classMethods: {
            associate: (models) => {
                Baterias.belongsTo(models.Surfistas, {as: 'Surfistas'});
            }
        }
    });
    return Baterias;
};