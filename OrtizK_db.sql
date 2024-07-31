DROP DATABASE pacientes_db;
CREATE DATABASE pacientes_db;
USE pacientes_db;

CREATE TABLE Genero (
		codigoGenero INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(codigoGenero),
    genero VARCHAR(20)
);

CREATE TABLE Paciente (
		codigoPaciente INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(10) UNIQUE NOT NULL,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    fechaNacimiento VARCHAR(10),
    email VARCHAR(50),
    codigoGenero INT,
    CONSTRAINT FK_id_genero FOREIGN KEY (codigoGenero) REFERENCES Genero(codigoGenero)
);

CREATE TABLE RegistroVital (
		codigoRegistro INT PRIMARY KEY AUTO_INCREMENT,
    codigoPaciente INT,
    fechaYHora VARCHAR(10),
    pulso INT,
    saturacion_oxigeno DECIMAL(4,1),
    CONSTRAINT FK_codigo_paciente FOREIGN KEY (codigoPaciente) REFERENCES Paciente(codigoPaciente)
);
