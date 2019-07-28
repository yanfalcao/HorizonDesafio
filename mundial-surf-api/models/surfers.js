module.exports = (sequelize, DataType) => {
    const Surfistas = sequelize.define("Surfistas", {
        id: {
            type: DataType.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        nome: {
            type: DataType.STRING,
            allowNull: false,
            validate: {
                notEmpty: true
            }
        },
        pais: {
            type: DataType.STRING,
            allowNull: false,
            validate: {
                notEmpty: true
            }
        }
    },  {
        classMethods: {
            associate: (models) => {
                Surfistas.hasMany(models.Baterias, { as: 'Baterias'});
            }
        }
    });
    return Surfistas;
};