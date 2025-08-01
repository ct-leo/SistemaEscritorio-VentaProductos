CREATE DATABASE BDVENTA_PRODUCTOS_INFORMATICOS
USE BDVENTA_PRODUCTOS_INFORMATICOS

CREATE TABLE CLIENTE(
ID_CLIENTE INT AUTO_INCREMENT PRIMARY KEY,
COD_CLIENTE CHAR(7) NOT NULL,
NOMBRE VARCHAR(40) NOT NULL,
APELLIDO VARCHAR(40) NOT NULL,
CORREO VARCHAR(40) NOT NULL,
TELEFONO CHAR(9) NOT NULL,
DIRECCION TEXT NOT NULL
);

CREATE TABLE PRODUCTO(
ID_PRODUCTO INT AUTO_INCREMENT PRIMARY KEY,
COD_PRODUCTO CHAR(7) NOT NULL,
NOMBRE VARCHAR(40) NOT NULL,
DESCRIPCION TEXT NOT NULL,
PRECIO DOUBLE NOT NULL,
STOCK INT NOT NULL,
CATEGORIA VARCHAR(40) NOT NULL,
MARCA VARCHAR(40) NOT NULL,
PROVEEDOR VARCHAR(40) NOT NULL
);

CREATE TABLE CATEGORIA(
ID_CATEGORIA INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE VARCHAR(40) NOT NULL,
CANTIDAD_TOTAL INT NOT NULL,
DESCRIPCION TEXT NOT NULL
);

CREATE TABLE PEDIDO(
ID_PEDIDO INT AUTO_INCREMENT PRIMARY KEY,
COD_PEDIDO CHAR(7) NOT NULL,
NOMBRE_CLIENTE VARCHAR(40) NOT NULL,
FECHA_PEDIDO DATE NOT NULL,
MONTO_TOTAL DOUBLE NOT NULL,
ESTADO TEXT NOT NULL
);

CREATE TABLE DETALLE_PEDIDO(
ID_DETALLE_PED INT AUTO_INCREMENT PRIMARY KEY,
COD_PEDIDO CHAR(7) NOT NULL,
COD_PRODUCTO CHAR(7) NOT NULL,
COD_CLIENTE CHAR(7) NOT NULL,
CANTIDAD INT NOT NULL,
PRECIO_UNITARIO DOUBLE NOT NULL
);

CREATE TABLE PROVEEDOR(
ID_PROVEEDOR INT AUTO_INCREMENT PRIMARY KEY,
COD_PROVEEDOR CHAR(7) NOT NULL,
NOMBRE VARCHAR(40) NOT NULL,
RUC CHAR(11) NOT NULL,
TELEFONO CHAR(9) NOT NULL,
CORREO VARCHAR(40) NOT NULL,
DIRECCION TEXT NOT NULL,
PAIS VARCHAR(40) NOT NULL
);

CREATE TABLE MARCA(
ID_MARCA INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE VARCHAR(40) NOT NULL,
PAIS VARCHAR(40) NOT NULL,
COD_PROVEEDOR CHAR(7) NOT NULL
);

CREATE TABLE NOTIFICACION(
ID_NOTI INT AUTO_INCREMENT PRIMARY KEY,
COD_PROVEEDOR CHAR(7) NOT NULL,
ASUNTO TEXT NOT NULL,
MENSAJE TEXT NOT NULL,
FECHA DATE NOT NULL,
ESTADO VARCHAR(20) NOT NULL
);

SELECT * FROM cliente
INSERT INTO CLIENTE VALUES
(NULL, 'CLI0001', 'Juan', 'Pérez', 'juanp@gmail.com', '987654321', 'Av. Siempre Viva 123'),
(NULL, 'CLI0002', 'Ana', 'López', 'ana.lopez@hotmail.com', '912345678', 'Calle Falsa 456'),
(NULL, 'CLI0003', 'Carlos', 'Ramírez', 'carlos.r@gmail.com', '923456789', 'Jr. Amazonas 789'),
(NULL, 'CLI0004', 'Luis', 'Torres', 'ltorres@yahoo.com', '934567890', 'Av. Arequipa 101'),
(NULL, 'CLI0005', 'María', 'Gómez', 'maria.gomez@gmail.com', '945678901', 'Calle Lima 202'),
(NULL, 'CLI0006', 'Diana', 'Quispe', 'dianaq@outlook.com', '956789012', 'Pasaje Sol 303'),
(NULL, 'CLI0007', 'Pedro', 'Castro', 'pcastro@gmail.com', '967890123', 'Av. Brasil 404'),
(NULL, 'CLI0008', 'Lucía', 'Mendoza', 'lucia.mz@icloud.com', '978901234', 'Jr. Cusco 505'),
(NULL, 'CLI0009', 'Jorge', 'Vargas', 'jvargas@live.com', '989012345', 'Av. Angamos 606'),
(NULL, 'CLI0010', 'Elena', 'Ruiz', 'elenar@hotmail.com', '990123456', 'Calle Uno 707'),
(NULL, 'CLI0011', 'Ricardo', 'Fernández', 'rfernandez@gmail.com', '901234567', 'Jr. Maranga 808'),
(NULL, 'CLI0012', 'Rosa', 'Salazar', 'rosas@gmail.com', '912345670', 'Av. Grau 909'),
(NULL, 'CLI0013', 'Miguel', 'Soto', 'migsoto@yahoo.com', '923456781', 'Calle Dos 010'),
(NULL, 'CLI0014', 'Isabel', 'Rojas', 'isabelr@hotmail.com', '934567892', 'Av. Wilson 111'),
(NULL, 'CLI0015', 'Daniel', 'Chávez', 'dchavez@gmail.com', '945678903', 'Calle Tres 212'),
(NULL, 'CLI0016', 'Valeria', 'Delgado', 'valed@gmail.com', '956789014', 'Jr. Lampa 313'),
(NULL, 'CLI0017', 'Andrés', 'Mejía', 'amejia@hotmail.com', '967890125', 'Av. Benavides 414'),
(NULL, 'CLI0018', 'Sandra', 'Morales', 'smorales@gmail.com', '978901236', 'Calle Los Olivos 515'),
(NULL, 'CLI0019', 'Fabio', 'Palacios', 'fpalacios@yahoo.com', '989012347', 'Jr. Colmena 616'),
(NULL, 'CLI0020', 'Natalia', 'Zúñiga', 'nataliaz@gmail.com', '990123458', 'Av. El Polo 717');


SELECT * FROM producto
INSERT INTO PRODUCTO VALUES
(NULL, 'PRD0001', 'Mouse Gamer', 'Mouse óptico con luces LED y 6 botones programables', 89.90, 50, 'Periféricos', 'Logitech', 'PROV001'),
(NULL, 'PRD0002', 'Teclado Mecánico', 'Teclado con switches azules y retroiluminación RGB', 159.00, 30, 'Periféricos', 'HyperX', 'PROV002'),
(NULL, 'PRD0003', 'Monitor 24"', 'Monitor LED Full HD de 24 pulgadas', 499.90, 20, 'Monitores', 'LG', 'PROV003'),
(NULL, 'PRD0004', 'Procesador Ryzen 5', 'CPU Ryzen 5 5600X de 6 núcleos', 899.00, 15, 'Componentes', 'AMD', 'PROV004'),
(NULL, 'PRD0005', 'Memoria RAM 16GB', 'RAM DDR4 3200MHz', 239.00, 40, 'Componentes', 'Corsair', 'PROV005'),
(NULL, 'PRD0006', 'Placa Madre B550', 'Soporte para Ryzen, DDR4, M.2', 519.00, 18, 'Componentes', 'ASUS', 'PROV006'),
(NULL, 'PRD0007', 'Disco SSD 1TB', 'SSD NVMe Gen3x4', 349.00, 25, 'Almacenamiento', 'Western Digital', 'PROV007'),
(NULL, 'PRD0008', 'Case Mid Tower', 'Case con ventana de vidrio templado', 279.00, 22, 'Gabinetes', 'NZXT', 'PROV008'),
(NULL, 'PRD0009', 'Fuente 650W', 'Fuente certificada 80+ Bronze', 319.00, 27, 'Componentes', 'EVGA', 'PROV009'),
(NULL, 'PRD0010', 'Tarjeta Gráfica RTX 3060', 'GPU NVIDIA con 12GB GDDR6', 1399.00, 10, 'Tarjetas de Video', 'MSI', 'PROV010'),
(NULL, 'PRD0011', 'Audífonos Gamer', 'Con sonido 7.1 y micrófono', 199.90, 35, 'Periféricos', 'Razer', 'PROV020'),
(NULL, 'PRD0012', 'Webcam Full HD', 'Cámara 1080p con micrófono', 149.90, 28, 'Accesorios', 'Logitech', 'PROV001'),
(NULL, 'PRD0013', 'Disco HDD 2TB', 'HDD 7200 RPM', 269.90, 30, 'Almacenamiento', 'Seagate', 'PROV007'),
(NULL, 'PRD0014', 'Cooler CPU', 'Enfriador por aire con RGB', 129.90, 20, 'Componentes', 'Cooler Master', 'PROV011'),
(NULL, 'PRD0015', 'Router WiFi 6', 'Router de alta velocidad AX1800', 199.00, 18, 'Redes', 'TP-Link', 'PROV012'),
(NULL, 'PRD0016', 'Laptop i5 11va', 'Laptop 8GB RAM, SSD 512GB', 2399.00, 12, 'Laptops', 'HP', 'PROV013'),
(NULL, 'PRD0017', 'Mousepad XXL', 'Alfombrilla gamer extendida', 79.00, 50, 'Accesorios', 'Razer', 'PROV020'),
(NULL, 'PRD0018', 'Switch Red 8P', 'Switch de red de 8 puertos', 89.00, 15, 'Redes', 'TP-Link', 'PROV012'),
(NULL, 'PRD0019', 'Estabilizador 1000VA', 'Protección eléctrica para PC', 149.00, 10, 'Accesorios', 'Forza', 'PROV014'),
(NULL, 'PRD0020', 'Impresora Multifuncional', 'Impresora, escáner y copiadora', 599.00, 9, 'Impresoras', 'Canon', 'PROV015');

SELECT * FROM categoria
INSERT INTO CATEGORIA VALUES
(NULL, 'Periféricos', 115, 'Accesorios como teclados, ratones, audífonos, etc.'),
(NULL, 'Monitores', 20, 'Pantallas LED, LCD y curvos'),
(NULL, 'Componentes', 143, 'Piezas principales como CPU, RAM, placas madre'),
(NULL, 'Almacenamiento', 55, 'Discos SSD y HDD'),
(NULL, 'Gabinetes', 22, 'Estructura de la PC'),
(NULL, 'Tarjetas de Video', 10, 'GPUs NVIDIA y AMD'),
(NULL, 'Accesorios', 108, 'Complementos varios como webcam, mousepad'),
(NULL, 'Redes', 33, 'Routers, switches y tarjetas de red'),
(NULL, 'Laptops', 12, 'Portátiles de distintas gamas'),
(NULL, 'Impresoras', 9, 'Multifuncionales y láser'),
(NULL, 'Enfriamiento', 20, 'Coolers y soluciones térmicas'),
(NULL, 'UPS / Estabilizadores', 10, 'Protección eléctrica'),
(NULL, 'Software', 10, 'Sistemas operativos y licencias'),
(NULL, 'Servidores', 7, 'Equipos empresariales'),
(NULL, 'Conectores', 45, 'Cables y adaptadores'),
(NULL, 'Streaming', 35, 'Cámaras y micrófonos'),
(NULL, 'Gaming', 50, 'Productos para gamers'),
(NULL, 'Smart Home', 30, 'Domótica y automatización'),
(NULL, 'Proyectores', 20, 'Proyectores multimedia'),
(NULL, 'Tablets', 50, 'Dispositivos móviles tipo tablet');

SELECT * FROM pedido
INSERT INTO PEDIDO VALUES
(NULL, 'PED0001', 'Juan', '2025-04-01', 269.7, 'Entregado'),
(NULL, 'PED0002', 'Ana', '2025-04-02', 319.00, 'Pendiente'),
(NULL, 'PED0003', 'Carlos', '2025-04-03', 1399.00, 'Entregado'),
(NULL, 'PED0004', 'Luis', '2025-04-04', 199.90, 'Cancelado'),
(NULL, 'PED0005', 'María', '2025-04-05', 519.00, 'En reparto'),
(NULL, 'PED0006', 'Diana', '2025-04-06', 89.00, 'Entregado'),
(NULL, 'PED0007', 'Pedro', '2025-04-07', 269.90, 'Pendiente'),
(NULL, 'PED0008', 'Lucía', '2025-04-08', 599.00, 'Entregado'),
(NULL, 'PED0009', 'Jorge', '2025-04-09', 239.00, 'Pendiente'),
(NULL, 'PED0010', 'Elena', '2025-04-10', 149.00, 'En almacén'),
(NULL, 'PED0011', 'Ricardo', '2025-04-11', 899.00, 'Entregado'),
(NULL, 'PED0012', 'Rosa', '2025-04-12', 159.00, 'Entregado'),
(NULL, 'PED0013', 'Miguel', '2025-04-13', 349.00, 'Pendiente'),
(NULL, 'PED0014', 'Isabel', '2025-04-14', 129.90, 'Entregado'),
(NULL, 'PED0015', 'Daniel', '2025-04-15', 199.00, 'Entregado'),
(NULL, 'PED0016', 'Valeria', '2025-04-16', 499.90, 'Pendiente'),
(NULL, 'PED0017', 'Andrés', '2025-04-17', 279.00, 'Entregado'),
(NULL, 'PED0018', 'Sandra', '2025-04-18', 89.90, 'Pendiente'),
(NULL, 'PED0019', 'Fabio', '2025-04-19', 199.90, 'Cancelado'),
(NULL, 'PED0020', 'Natalia', '2025-04-20', 149.90, 'Entregado');

INSERT INTO DETALLE_PEDIDO VALUES
(NULL, 'PED0001', 'PRD0001', 'CLI0001', 3, 89.90),
(NULL, 'PED0002', 'PRD0009', 'CLI0002', 1, 319.00),
(NULL, 'PED0003', 'PRD0010', 'CLI0003', 1, 1399.00),
(NULL, 'PED0004', 'PRD0011', 'CLI0004', 1, 199.90),
(NULL, 'PED0005', 'PRD0006', 'CLI0005', 1, 519.00),
(NULL, 'PED0006', 'PRD0018', 'CLI0006', 1, 89.00),
(NULL, 'PED0007', 'PRD0013', 'CLI0007', 1, 269.90),
(NULL, 'PED0008', 'PRD0020', 'CLI0008', 1, 599.00),
(NULL, 'PED0009', 'PRD0005', 'CLI0009', 1, 239.00),
(NULL, 'PED0010', 'PRD0019', 'CLI0010', 1, 149.00),
(NULL, 'PED0011', 'PRD0004', 'CLI0011', 1, 899.00),
(NULL, 'PED0012', 'PRD0002', 'CLI0012', 1, 159.00),
(NULL, 'PED0013', 'PRD0007', 'CLI0013', 1, 349.00),
(NULL, 'PED0014', 'PRD0014', 'CLI0014', 1, 129.90),
(NULL, 'PED0015', 'PRD0015', 'CLI0015', 1, 199.00),
(NULL, 'PED0016', 'PRD0003', 'CLI0016', 1, 499.90),
(NULL, 'PED0017', 'PRD0008', 'CLI0017', 1, 279.00),
(NULL, 'PED0018', 'PRD0017', 'CLI0018', 1, 89.90),
(NULL, 'PED0019', 'PRD0011', 'CLI0019', 1, 199.90),
(NULL, 'PED0020', 'PRD0012', 'CLI0020', 1, 149.90);

SELECT * FROM proveedor
INSERT INTO PROVEEDOR VALUES
(NULL, 'PROV001', 'Logitech', '20304050607', '987654321', 'contacto@logitech.com', 'Av. Internacional 123', 'EE.UU.'),
(NULL, 'PROV002', 'HyperX', '20304050608', '912345678', 'ventas@hyperx.com', 'Calle Gaming 456', 'EE.UU.'),
(NULL, 'PROV003', 'LG', '20304050609', '923456789', 'info@lg.com', 'Av. Corea 789', 'Corea del Sur'),
(NULL, 'PROV004', 'AMD', '20304050610', '934567890', 'contact@amd.com', 'Av. Silicon 101', 'EE.UU.'),
(NULL, 'PROV005', 'Corsair', '20304050611', '945678901', 'ventas@corsair.com', 'Tech Street 202', 'EE.UU.'),
(NULL, 'PROV006', 'ASUS', '20304050612', '956789012', 'support@asus.com', 'Taipei Tech Park', 'Taiwán'),
(NULL, 'PROV007', 'Western Digital', '20304050613', '967890123', 'wd@storage.com', 'WD Avenue 404', 'EE.UU.'),
(NULL, 'PROV008', 'NZXT', '20304050614', '978901234', 'support@nzxt.com', 'Design Way 505', 'EE.UU.'),
(NULL, 'PROV009', 'EVGA', '20304050615', '989012345', 'info@evga.com', 'EVGA Road 606', 'EE.UU.'),
(NULL, 'PROV010', 'MSI', '20304050616', '990123456', 'msi@global.com', 'Gaming St 707', 'Taiwán'),
(NULL, 'PROV011', 'Cooler Master', '20304050617', '901234567', 'ventas@coolermaster.com', 'Cooling Av. 808', 'Taiwán'),
(NULL, 'PROV012', 'TP-Link', '20304050618', '912345670', 'soporte@tplink.com', 'Link St. 909', 'China'),
(NULL, 'PROV013', 'HP', '20304050619', '923456781', 'hp@contact.com', 'HP Way 010', 'EE.UU.'),
(NULL, 'PROV014', 'Forza', '20304050620', '934567892', 'forza@energía.com', 'Calle Energía 111', 'Perú'),
(NULL, 'PROV015', 'Canon', '20304050621', '945678903', 'contacto@canon.com', 'Canon Plaza 212', 'Japón'),
(NULL, 'PROV016', 'Samsung', '20304050622', '956789014', 'ventas@samsung.com', 'Digital City 313', 'Corea del Sur'),
(NULL, 'PROV017', 'Dell', '20304050623', '967890125', 'info@dell.com', 'Dell Park 414', 'EE.UU.'),
(NULL, 'PROV018', 'Lenovo', '20304050624', '978901236', 'ventas@lenovo.com', 'Lenovo Tech Zone', 'China'),
(NULL, 'PROV019', 'Xiaomi', '20304050625', '989012347', 'mi@xiaomi.com', 'Xiaomi Building', 'China'),
(NULL, 'PROV020', 'Razen', '20304050626', '990123458', 'tt@razen.com', 'Cool St 717', 'EE.UU.');

SELECT * FROM marca
INSERT INTO MARCA VALUES
(NULL, 'Logitech', 'EE.UU.', 'PROV001'),
(NULL, 'HyperX', 'EE.UU.', 'PROV002'),
(NULL, 'LG', 'Corea del Sur', 'PROV003'),
(NULL, 'AMD', 'EE.UU.', 'PROV004'),
(NULL, 'Corsair', 'EE.UU.', 'PROV005'),
(NULL, 'ASUS', 'Taiwán', 'PROV006'),
(NULL, 'Western Digital', 'EE.UU.', 'PROV007'),
(NULL, 'NZXT', 'EE.UU.', 'PROV008'),
(NULL, 'EVGA', 'EE.UU.', 'PROV009'),
(NULL, 'MSI', 'Taiwán', 'PROV010'),
(NULL, 'Cooler Master', 'Taiwán', 'PROV011'),
(NULL, 'TP-Link', 'China', 'PROV012'),
(NULL, 'HP', 'EE.UU.', 'PROV013'),
(NULL, 'Forza', 'Perú', 'PROV014'),
(NULL, 'Canon', 'Japón', 'PROV015'),
(NULL, 'Samsung', 'Corea del Sur', 'PROV016'),
(NULL, 'Dell', 'EE.UU.', 'PROV017'),
(NULL, 'Lenovo', 'China', 'PROV018'),
(NULL, 'Xiaomi', 'China', 'PROV019'),
(NULL, 'Razen', 'EE.UU.', 'PROV020');

SELECT * FROM notificacion
INSERT INTO NOTIFICACION VALUES
(NULL, 'PROV001', 'Demora en despacho', 'Tu pedido está retrasado por problemas logísticos.', '2025-04-01', 'Pendiente'),
(NULL, 'PROV002', 'Stock agotado', 'No hay más teclados disponibles hasta nuevo aviso.', '2025-04-02', 'Resuelto'),
(NULL, 'PROV003', 'Nueva promoción', 'Descuentos en monitores LG por tiempo limitado.', '2025-04-03', 'Activo'),
(NULL, 'PROV004', 'Actualización de precios', 'Nuevas tarifas en procesadores Ryzen.', '2025-04-04', 'Resuelto'),
(NULL, 'PROV005', 'Solicitud de pago', 'Favor realizar el pago correspondiente al pedido 456.', '2025-04-05', 'Pendiente'),
(NULL, 'PROV006', 'Entrega parcial', 'Solo se entregó parte del pedido.', '2025-04-06', 'Pendiente'),
(NULL, 'PROV007', 'Producto defectuoso', 'Cliente reportó problemas con el SSD.', '2025-04-07', 'Resuelto'),
(NULL, 'PROV008', 'Demora en aduanas', 'Pedido retenido en aduanas por verificación.', '2025-04-08', 'Pendiente'),
(NULL, 'PROV009', 'Factura enviada', 'Adjuntamos la factura electrónica.', '2025-04-09', 'Enviado'),
(NULL, 'PROV010', 'Nuevo catálogo', 'Ya está disponible el catálogo 2025.', '2025-04-10', 'Activo'),
(NULL, 'PROV011', 'Actualización técnica', 'Nuevo cooler compatible con AM5.', '2025-04-11', 'Activo'),
(NULL, 'PROV012', 'Cambio de condiciones', 'Nuevos términos para la compra mínima.', '2025-04-12', 'Pendiente'),
(NULL, 'PROV013', 'Reclamo pendiente', 'Cliente solicita devolución.', '2025-04-13', 'Pendiente'),
(NULL, 'PROV014', 'Revisión técnica', 'Verificar estabilizador antes del envío.', '2025-04-14', 'Pendiente'),
(NULL, 'PROV015', 'Impresora sin stock', 'Producto agotado temporalmente.', '2025-04-15', 'Resuelto'),
(NULL, 'PROV016', 'Demora logística', 'Pedido con retraso por falta de transporte.', '2025-04-16', 'Pendiente'),
(NULL, 'PROV017', 'Solicitud rechazada', 'No se aprobó el pedido de laptops.', '2025-04-17', 'Resuelto'),
(NULL, 'PROV018', 'Confirmación de pago', 'Se ha recibido el abono correspondiente.', '2025-04-18', 'Confirmado'),
(NULL, 'PROV019', 'Falla en entrega', 'Problemas con dirección de envío.', '2025-04-19', 'Pendiente'),
(NULL, 'PROV020', 'Nuevo modelo disponible', 'Refrigeración líquida TT 2025 disponible.', '2025-04-20', 'Activo');

