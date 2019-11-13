package folder1.folder2
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Array(Agenda.schema, Bonos.schema, Clientes.schema, ClientesBonos.schema, Compras.schema, ComprasProductos.schema, Departamentos.schema, Empleados.schema, Pedidos.schema, PedidosProductos.schema, Personas.schema, Productos.schema, ProductosDepartamentos.schema, Proveedores.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Agenda
   *  @param idTarea Database column id_tarea SqlType(INT), AutoInc, PrimaryKey
   *  @param idEmpleado Database column id_empleado SqlType(INT)
   *  @param descripcion Database column descripcion SqlType(VARCHAR), Length(255,true)
   *  @param fechaExpiracion Database column fecha_expiracion SqlType(DATETIME)
   *  @param fechaCreacion Database column fecha_creacion SqlType(DATETIME) */
  case class AgendaRow(idTarea: Int, idEmpleado: Int, descripcion: String, fechaExpiracion: java.sql.Timestamp, fechaCreacion: java.sql.Timestamp)
  /** GetResult implicit for fetching AgendaRow objects using plain SQL queries */
  implicit def GetResultAgendaRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[AgendaRow] = GR{
    prs => import prs._
    AgendaRow.tupled((<<[Int], <<[Int], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table Agenda. Objects of this class serve as prototypes for rows in queries. */
  class Agenda(_tableTag: Tag) extends Table[AgendaRow](_tableTag, "Agenda") {
    def * = (idTarea, idEmpleado, descripcion, fechaExpiracion, fechaCreacion) <> (AgendaRow.tupled, AgendaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idTarea), Rep.Some(idEmpleado), Rep.Some(descripcion), Rep.Some(fechaExpiracion), Rep.Some(fechaCreacion)).shaped.<>({r=>import r._; _1.map(_=> AgendaRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_tarea SqlType(INT), AutoInc, PrimaryKey */
    val idTarea: Rep[Int] = column[Int]("id_tarea", O.AutoInc, O.PrimaryKey)
    /** Database column id_empleado SqlType(INT) */
    val idEmpleado: Rep[Int] = column[Int]("id_empleado")
    /** Database column descripcion SqlType(VARCHAR), Length(255,true) */
    val descripcion: Rep[String] = column[String]("descripcion", O.Length(255,varying=true))
    /** Database column fecha_expiracion SqlType(DATETIME) */
    val fechaExpiracion: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("fecha_expiracion")
    /** Database column fecha_creacion SqlType(DATETIME) */
    val fechaCreacion: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("fecha_creacion")

    /** Foreign key referencing Empleados (database name Agenda_ibfk_1) */
    lazy val empleadosFk = foreignKey("Agenda_ibfk_1", idEmpleado, Empleados)(r => r.idEmpleado, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Agenda */
  lazy val Agenda = new TableQuery(tag => new Agenda(tag))

  /** Entity class storing rows of table Bonos
   *  @param idBono Database column id_bono SqlType(INT), AutoInc, PrimaryKey
   *  @param nombre Database column nombre SqlType(VARCHAR), Length(255,true)
   *  @param descripcion Database column descripcion SqlType(VARCHAR), Length(255,true)
   *  @param porcentajeDescuento Database column porcentaje_descuento SqlType(INT) */
  case class BonosRow(idBono: Int, nombre: String, descripcion: String, porcentajeDescuento: Int)
  /** GetResult implicit for fetching BonosRow objects using plain SQL queries */
  implicit def GetResultBonosRow(implicit e0: GR[Int], e1: GR[String]): GR[BonosRow] = GR{
    prs => import prs._
    BonosRow.tupled((<<[Int], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table Bonos. Objects of this class serve as prototypes for rows in queries. */
  class Bonos(_tableTag: Tag) extends Table[BonosRow](_tableTag, "Bonos") {
    def * = (idBono, nombre, descripcion, porcentajeDescuento) <> (BonosRow.tupled, BonosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idBono), Rep.Some(nombre), Rep.Some(descripcion), Rep.Some(porcentajeDescuento)).shaped.<>({r=>import r._; _1.map(_=> BonosRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_bono SqlType(INT), AutoInc, PrimaryKey */
    val idBono: Rep[Int] = column[Int]("id_bono", O.AutoInc, O.PrimaryKey)
    /** Database column nombre SqlType(VARCHAR), Length(255,true) */
    val nombre: Rep[String] = column[String]("nombre", O.Length(255,varying=true))
    /** Database column descripcion SqlType(VARCHAR), Length(255,true) */
    val descripcion: Rep[String] = column[String]("descripcion", O.Length(255,varying=true))
    /** Database column porcentaje_descuento SqlType(INT) */
    val porcentajeDescuento: Rep[Int] = column[Int]("porcentaje_descuento")
  }
  /** Collection-like TableQuery object for table Bonos */
  lazy val Bonos = new TableQuery(tag => new Bonos(tag))

  /** Entity class storing rows of table Clientes
   *  @param idCliente Database column id_cliente SqlType(INT), AutoInc, PrimaryKey
   *  @param idPersona Database column id_persona SqlType(INT)
   *  @param dStartdate Database column d_startDate SqlType(DATETIME)
   *  @param dEnddate Database column d_endDate SqlType(DATETIME) */
  case class ClientesRow(idCliente: Int, idPersona: Int, dStartdate: java.sql.Timestamp, dEnddate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ClientesRow objects using plain SQL queries */
  implicit def GetResultClientesRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[java.sql.Timestamp]]): GR[ClientesRow] = GR{
    prs => import prs._
    ClientesRow.tupled((<<[Int], <<[Int], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Clientes. Objects of this class serve as prototypes for rows in queries. */
  class Clientes(_tableTag: Tag) extends Table[ClientesRow](_tableTag, "Clientes") {
    def * = (idCliente, idPersona, dStartdate, dEnddate) <> (ClientesRow.tupled, ClientesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idCliente), Rep.Some(idPersona), Rep.Some(dStartdate), dEnddate).shaped.<>({r=>import r._; _1.map(_=> ClientesRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_cliente SqlType(INT), AutoInc, PrimaryKey */
    val idCliente: Rep[Int] = column[Int]("id_cliente", O.AutoInc, O.PrimaryKey)
    /** Database column id_persona SqlType(INT) */
    val idPersona: Rep[Int] = column[Int]("id_persona")
    /** Database column d_startDate SqlType(DATETIME) */
    val dStartdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("d_startDate")
    /** Database column d_endDate SqlType(DATETIME) */
    val dEnddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("d_endDate")

    /** Foreign key referencing Personas (database name Clientes_ibfk_1) */
    lazy val personasFk = foreignKey("Clientes_ibfk_1", idPersona, Personas)(r => r.idPersona, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Clientes */
  lazy val Clientes = new TableQuery(tag => new Clientes(tag))

  /** Entity class storing rows of table ClientesBonos
   *  @param idClienteBono Database column id_cliente_bono SqlType(INT), AutoInc, PrimaryKey
   *  @param idCliente Database column id_cliente SqlType(INT)
   *  @param idBono Database column id_bono SqlType(INT)
   *  @param fechaCreacion Database column fecha_creacion SqlType(DATE)
   *  @param fechaExpiracion Database column fecha_expiracion SqlType(DATE) */
  case class ClientesBonosRow(idClienteBono: Int, idCliente: Int, idBono: Int, fechaCreacion: java.sql.Date, fechaExpiracion: java.sql.Date)
  /** GetResult implicit for fetching ClientesBonosRow objects using plain SQL queries */
  implicit def GetResultClientesBonosRow(implicit e0: GR[Int], e1: GR[java.sql.Date]): GR[ClientesBonosRow] = GR{
    prs => import prs._
    ClientesBonosRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Date], <<[java.sql.Date]))
  }
  /** Table description of table Clientes_Bonos. Objects of this class serve as prototypes for rows in queries. */
  class ClientesBonos(_tableTag: Tag) extends Table[ClientesBonosRow](_tableTag, "Clientes_Bonos") {
    def * = (idClienteBono, idCliente, idBono, fechaCreacion, fechaExpiracion) <> (ClientesBonosRow.tupled, ClientesBonosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idClienteBono), Rep.Some(idCliente), Rep.Some(idBono), Rep.Some(fechaCreacion), Rep.Some(fechaExpiracion)).shaped.<>({r=>import r._; _1.map(_=> ClientesBonosRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_cliente_bono SqlType(INT), AutoInc, PrimaryKey */
    val idClienteBono: Rep[Int] = column[Int]("id_cliente_bono", O.AutoInc, O.PrimaryKey)
    /** Database column id_cliente SqlType(INT) */
    val idCliente: Rep[Int] = column[Int]("id_cliente")
    /** Database column id_bono SqlType(INT) */
    val idBono: Rep[Int] = column[Int]("id_bono")
    /** Database column fecha_creacion SqlType(DATE) */
    val fechaCreacion: Rep[java.sql.Date] = column[java.sql.Date]("fecha_creacion")
    /** Database column fecha_expiracion SqlType(DATE) */
    val fechaExpiracion: Rep[java.sql.Date] = column[java.sql.Date]("fecha_expiracion")

    /** Foreign key referencing Bonos (database name Clientes_Bonos_ibfk_2) */
    lazy val bonosFk = foreignKey("Clientes_Bonos_ibfk_2", idBono, Bonos)(r => r.idBono, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Clientes (database name Clientes_Bonos_ibfk_1) */
    lazy val clientesFk = foreignKey("Clientes_Bonos_ibfk_1", idCliente, Clientes)(r => r.idCliente, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ClientesBonos */
  lazy val ClientesBonos = new TableQuery(tag => new ClientesBonos(tag))

  /** Entity class storing rows of table Compras
   *  @param idCompra Database column id_compra SqlType(INT), AutoInc, PrimaryKey
   *  @param idCliente Database column id_cliente SqlType(INT)
   *  @param idEmpleado Database column id_empleado SqlType(INT)
   *  @param idBono Database column id_bono SqlType(INT), Default(None)
   *  @param dStartdate Database column d_startDate SqlType(DATETIME) */
  case class ComprasRow(idCompra: Int, idCliente: Int, idEmpleado: Int, idBono: Option[Int] = None, dStartdate: java.sql.Timestamp)
  /** GetResult implicit for fetching ComprasRow objects using plain SQL queries */
  implicit def GetResultComprasRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[ComprasRow] = GR{
    prs => import prs._
    ComprasRow.tupled((<<[Int], <<[Int], <<[Int], <<?[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table Compras. Objects of this class serve as prototypes for rows in queries. */
  class Compras(_tableTag: Tag) extends Table[ComprasRow](_tableTag, "Compras") {
    def * = (idCompra, idCliente, idEmpleado, idBono, dStartdate) <> (ComprasRow.tupled, ComprasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idCompra), Rep.Some(idCliente), Rep.Some(idEmpleado), idBono, Rep.Some(dStartdate)).shaped.<>({r=>import r._; _1.map(_=> ComprasRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_compra SqlType(INT), AutoInc, PrimaryKey */
    val idCompra: Rep[Int] = column[Int]("id_compra", O.AutoInc, O.PrimaryKey)
    /** Database column id_cliente SqlType(INT) */
    val idCliente: Rep[Int] = column[Int]("id_cliente")
    /** Database column id_empleado SqlType(INT) */
    val idEmpleado: Rep[Int] = column[Int]("id_empleado")
    /** Database column id_bono SqlType(INT), Default(None) */
    val idBono: Rep[Option[Int]] = column[Option[Int]]("id_bono", O.Default(None))
    /** Database column d_startDate SqlType(DATETIME) */
    val dStartdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("d_startDate")

    /** Foreign key referencing Bonos (database name Compras_ibfk_3) */
    lazy val bonosFk = foreignKey("Compras_ibfk_3", idBono, Bonos)(r => Rep.Some(r.idBono), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Clientes (database name Compras_ibfk_1) */
    lazy val clientesFk = foreignKey("Compras_ibfk_1", idCliente, Clientes)(r => r.idCliente, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Empleados (database name Compras_ibfk_2) */
    lazy val empleadosFk = foreignKey("Compras_ibfk_2", idEmpleado, Empleados)(r => r.idEmpleado, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Compras */
  lazy val Compras = new TableQuery(tag => new Compras(tag))

  /** Entity class storing rows of table ComprasProductos
   *  @param idCompraProducto Database column id_compra_producto SqlType(INT), AutoInc, PrimaryKey
   *  @param idCompra Database column id_compra SqlType(INT)
   *  @param idProducto Database column id_producto SqlType(INT)
   *  @param cantidad Database column cantidad SqlType(INT)
   *  @param fechaCompra Database column fecha_compra SqlType(DATETIME) */
  case class ComprasProductosRow(idCompraProducto: Int, idCompra: Int, idProducto: Int, cantidad: Int, fechaCompra: java.sql.Timestamp)
  /** GetResult implicit for fetching ComprasProductosRow objects using plain SQL queries */
  implicit def GetResultComprasProductosRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[ComprasProductosRow] = GR{
    prs => import prs._
    ComprasProductosRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table Compras_Productos. Objects of this class serve as prototypes for rows in queries. */
  class ComprasProductos(_tableTag: Tag) extends Table[ComprasProductosRow](_tableTag, "Compras_Productos") {
    def * = (idCompraProducto, idCompra, idProducto, cantidad, fechaCompra) <> (ComprasProductosRow.tupled, ComprasProductosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idCompraProducto), Rep.Some(idCompra), Rep.Some(idProducto), Rep.Some(cantidad), Rep.Some(fechaCompra)).shaped.<>({r=>import r._; _1.map(_=> ComprasProductosRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_compra_producto SqlType(INT), AutoInc, PrimaryKey */
    val idCompraProducto: Rep[Int] = column[Int]("id_compra_producto", O.AutoInc, O.PrimaryKey)
    /** Database column id_compra SqlType(INT) */
    val idCompra: Rep[Int] = column[Int]("id_compra")
    /** Database column id_producto SqlType(INT) */
    val idProducto: Rep[Int] = column[Int]("id_producto")
    /** Database column cantidad SqlType(INT) */
    val cantidad: Rep[Int] = column[Int]("cantidad")
    /** Database column fecha_compra SqlType(DATETIME) */
    val fechaCompra: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("fecha_compra")

    /** Foreign key referencing Compras (database name Compras_Productos_ibfk_1) */
    lazy val comprasFk = foreignKey("Compras_Productos_ibfk_1", idCompra, Compras)(r => r.idCompra, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Productos (database name Compras_Productos_ibfk_2) */
    lazy val productosFk = foreignKey("Compras_Productos_ibfk_2", idProducto, Productos)(r => r.idProducto, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ComprasProductos */
  lazy val ComprasProductos = new TableQuery(tag => new ComprasProductos(tag))

  /** Entity class storing rows of table Departamentos
   *  @param idDepartamento Database column id_departamento SqlType(INT), AutoInc, PrimaryKey
   *  @param nombre Database column nombre SqlType(VARCHAR), Length(255,true)
   *  @param descripcion Database column descripcion SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param image Database column Image SqlType(LONGBLOB), Default(None) */
  case class DepartamentosRow(idDepartamento: Int, nombre: String, descripcion: Option[String] = None, image: Option[java.sql.Blob] = None)
  /** GetResult implicit for fetching DepartamentosRow objects using plain SQL queries */
  implicit def GetResultDepartamentosRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Blob]]): GR[DepartamentosRow] = GR{
    prs => import prs._
    DepartamentosRow.tupled((<<[Int], <<[String], <<?[String], <<?[java.sql.Blob]))
  }
  /** Table description of table Departamentos. Objects of this class serve as prototypes for rows in queries. */
  class Departamentos(_tableTag: Tag) extends Table[DepartamentosRow](_tableTag, "Departamentos") {
    def * = (idDepartamento, nombre, descripcion, image) <> (DepartamentosRow.tupled, DepartamentosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idDepartamento), Rep.Some(nombre), descripcion, image).shaped.<>({r=>import r._; _1.map(_=> DepartamentosRow.tupled((_1.get, _2.get, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_departamento SqlType(INT), AutoInc, PrimaryKey */
    val idDepartamento: Rep[Int] = column[Int]("id_departamento", O.AutoInc, O.PrimaryKey)
    /** Database column nombre SqlType(VARCHAR), Length(255,true) */
    val nombre: Rep[String] = column[String]("nombre", O.Length(255,varying=true))
    /** Database column descripcion SqlType(VARCHAR), Length(255,true), Default(None) */
    val descripcion: Rep[Option[String]] = column[Option[String]]("descripcion", O.Length(255,varying=true), O.Default(None))
    /** Database column Image SqlType(LONGBLOB), Default(None) */
    val image: Rep[Option[java.sql.Blob]] = column[Option[java.sql.Blob]]("Image", O.Default(None))
  }
  /** Collection-like TableQuery object for table Departamentos */
  lazy val Departamentos = new TableQuery(tag => new Departamentos(tag))

  /** Entity class storing rows of table Empleados
   *  @param idEmpleado Database column id_empleado SqlType(INT), AutoInc, PrimaryKey
   *  @param idPersona Database column id_persona SqlType(INT)
   *  @param dStartdate Database column d_startDate SqlType(DATETIME)
   *  @param dEnddate Database column d_endDate SqlType(DATETIME) */
  case class EmpleadosRow(idEmpleado: Int, idPersona: Int, dStartdate: java.sql.Timestamp, dEnddate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching EmpleadosRow objects using plain SQL queries */
  implicit def GetResultEmpleadosRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[java.sql.Timestamp]]): GR[EmpleadosRow] = GR{
    prs => import prs._
    EmpleadosRow.tupled((<<[Int], <<[Int], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Empleados. Objects of this class serve as prototypes for rows in queries. */
  class Empleados(_tableTag: Tag) extends Table[EmpleadosRow](_tableTag, "Empleados") {
    def * = (idEmpleado, idPersona, dStartdate, dEnddate) <> (EmpleadosRow.tupled, EmpleadosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idEmpleado), Rep.Some(idPersona), Rep.Some(dStartdate), dEnddate).shaped.<>({r=>import r._; _1.map(_=> EmpleadosRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_empleado SqlType(INT), AutoInc, PrimaryKey */
    val idEmpleado: Rep[Int] = column[Int]("id_empleado", O.AutoInc, O.PrimaryKey)
    /** Database column id_persona SqlType(INT) */
    val idPersona: Rep[Int] = column[Int]("id_persona")
    /** Database column d_startDate SqlType(DATETIME) */
    val dStartdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("d_startDate")
    /** Database column d_endDate SqlType(DATETIME) */
    val dEnddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("d_endDate")

    /** Foreign key referencing Personas (database name Empleados_ibfk_1) */
    lazy val personasFk = foreignKey("Empleados_ibfk_1", idPersona, Personas)(r => r.idPersona, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Empleados */
  lazy val Empleados = new TableQuery(tag => new Empleados(tag))

  /** Entity class storing rows of table Pedidos
   *  @param idPedido Database column id_pedido SqlType(INT), AutoInc, PrimaryKey
   *  @param idProveedor Database column id_proveedor SqlType(INT)
   *  @param idEmpleado Database column id_empleado SqlType(INT)
   *  @param fechaPedido Database column fecha_pedido SqlType(DATETIME)
   *  @param fechaLlegada Database column fecha_llegada SqlType(DATETIME), Default(None) */
  case class PedidosRow(idPedido: Int, idProveedor: Int, idEmpleado: Int, fechaPedido: java.sql.Timestamp, fechaLlegada: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching PedidosRow objects using plain SQL queries */
  implicit def GetResultPedidosRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[java.sql.Timestamp]]): GR[PedidosRow] = GR{
    prs => import prs._
    PedidosRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Pedidos. Objects of this class serve as prototypes for rows in queries. */
  class Pedidos(_tableTag: Tag) extends Table[PedidosRow](_tableTag, "Pedidos") {
    def * = (idPedido, idProveedor, idEmpleado, fechaPedido, fechaLlegada) <> (PedidosRow.tupled, PedidosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idPedido), Rep.Some(idProveedor), Rep.Some(idEmpleado), Rep.Some(fechaPedido), fechaLlegada).shaped.<>({r=>import r._; _1.map(_=> PedidosRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_pedido SqlType(INT), AutoInc, PrimaryKey */
    val idPedido: Rep[Int] = column[Int]("id_pedido", O.AutoInc, O.PrimaryKey)
    /** Database column id_proveedor SqlType(INT) */
    val idProveedor: Rep[Int] = column[Int]("id_proveedor")
    /** Database column id_empleado SqlType(INT) */
    val idEmpleado: Rep[Int] = column[Int]("id_empleado")
    /** Database column fecha_pedido SqlType(DATETIME) */
    val fechaPedido: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("fecha_pedido")
    /** Database column fecha_llegada SqlType(DATETIME), Default(None) */
    val fechaLlegada: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("fecha_llegada", O.Default(None))

    /** Foreign key referencing Empleados (database name Pedidos_ibfk_2) */
    lazy val empleadosFk = foreignKey("Pedidos_ibfk_2", idEmpleado, Empleados)(r => r.idEmpleado, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Proveedores (database name Pedidos_ibfk_1) */
    lazy val proveedoresFk = foreignKey("Pedidos_ibfk_1", idProveedor, Proveedores)(r => r.idProveedor, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Pedidos */
  lazy val Pedidos = new TableQuery(tag => new Pedidos(tag))

  /** Entity class storing rows of table PedidosProductos
   *  @param idPedidoProducto Database column id_pedido_producto SqlType(INT), AutoInc, PrimaryKey
   *  @param idPedido Database column id_pedido SqlType(INT)
   *  @param idProducto Database column id_producto SqlType(INT)
   *  @param cantidad Database column cantidad SqlType(INT)
   *  @param fechaPedido Database column fecha_pedido SqlType(DATETIME) */
  case class PedidosProductosRow(idPedidoProducto: Int, idPedido: Int, idProducto: Int, cantidad: Int, fechaPedido: java.sql.Timestamp)
  /** GetResult implicit for fetching PedidosProductosRow objects using plain SQL queries */
  implicit def GetResultPedidosProductosRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[PedidosProductosRow] = GR{
    prs => import prs._
    PedidosProductosRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table Pedidos_Productos. Objects of this class serve as prototypes for rows in queries. */
  class PedidosProductos(_tableTag: Tag) extends Table[PedidosProductosRow](_tableTag, "Pedidos_Productos") {
    def * = (idPedidoProducto, idPedido, idProducto, cantidad, fechaPedido) <> (PedidosProductosRow.tupled, PedidosProductosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idPedidoProducto), Rep.Some(idPedido), Rep.Some(idProducto), Rep.Some(cantidad), Rep.Some(fechaPedido)).shaped.<>({r=>import r._; _1.map(_=> PedidosProductosRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_pedido_producto SqlType(INT), AutoInc, PrimaryKey */
    val idPedidoProducto: Rep[Int] = column[Int]("id_pedido_producto", O.AutoInc, O.PrimaryKey)
    /** Database column id_pedido SqlType(INT) */
    val idPedido: Rep[Int] = column[Int]("id_pedido")
    /** Database column id_producto SqlType(INT) */
    val idProducto: Rep[Int] = column[Int]("id_producto")
    /** Database column cantidad SqlType(INT) */
    val cantidad: Rep[Int] = column[Int]("cantidad")
    /** Database column fecha_pedido SqlType(DATETIME) */
    val fechaPedido: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("fecha_pedido")

    /** Foreign key referencing Pedidos (database name Pedidos_Productos_ibfk_1) */
    lazy val pedidosFk = foreignKey("Pedidos_Productos_ibfk_1", idPedido, Pedidos)(r => r.idPedido, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Productos (database name Pedidos_Productos_ibfk_2) */
    lazy val productosFk = foreignKey("Pedidos_Productos_ibfk_2", idProducto, Productos)(r => r.idProducto, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table PedidosProductos */
  lazy val PedidosProductos = new TableQuery(tag => new PedidosProductos(tag))

  /** Entity class storing rows of table Personas
   *  @param idPersona Database column id_persona SqlType(INT), AutoInc, PrimaryKey
   *  @param nombre Database column nombre SqlType(VARCHAR), Length(255,true)
   *  @param primerapellido Database column primerApellido SqlType(VARCHAR), Length(255,true)
   *  @param segundoapellido Database column segundoApellido SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param fechaNacimiento Database column fecha_Nacimiento SqlType(DATE), Default(None)
   *  @param email Database column email SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param direccion Database column direccion SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param image Database column Image SqlType(LONGBLOB), Default(None) */
  case class PersonasRow(idPersona: Int, nombre: String, primerapellido: String, segundoapellido: Option[String] = None, fechaNacimiento: Option[java.sql.Date] = None, email: Option[String] = None, direccion: Option[String] = None, image: Option[java.sql.Blob] = None)
  /** GetResult implicit for fetching PersonasRow objects using plain SQL queries */
  implicit def GetResultPersonasRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Date]], e4: GR[Option[java.sql.Blob]]): GR[PersonasRow] = GR{
    prs => import prs._
    PersonasRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<?[java.sql.Date], <<?[String], <<?[String], <<?[java.sql.Blob]))
  }
  /** Table description of table Personas. Objects of this class serve as prototypes for rows in queries. */
  class Personas(_tableTag: Tag) extends Table[PersonasRow](_tableTag, "Personas") {
    def * = (idPersona, nombre, primerapellido, segundoapellido, fechaNacimiento, email, direccion, image) <> (PersonasRow.tupled, PersonasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idPersona), Rep.Some(nombre), Rep.Some(primerapellido), segundoapellido, fechaNacimiento, email, direccion, image).shaped.<>({r=>import r._; _1.map(_=> PersonasRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_persona SqlType(INT), AutoInc, PrimaryKey */
    val idPersona: Rep[Int] = column[Int]("id_persona", O.AutoInc, O.PrimaryKey)
    /** Database column nombre SqlType(VARCHAR), Length(255,true) */
    val nombre: Rep[String] = column[String]("nombre", O.Length(255,varying=true))
    /** Database column primerApellido SqlType(VARCHAR), Length(255,true) */
    val primerapellido: Rep[String] = column[String]("primerApellido", O.Length(255,varying=true))
    /** Database column segundoApellido SqlType(VARCHAR), Length(255,true), Default(None) */
    val segundoapellido: Rep[Option[String]] = column[Option[String]]("segundoApellido", O.Length(255,varying=true), O.Default(None))
    /** Database column fecha_Nacimiento SqlType(DATE), Default(None) */
    val fechaNacimiento: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("fecha_Nacimiento", O.Default(None))
    /** Database column email SqlType(VARCHAR), Length(255,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(255,varying=true), O.Default(None))
    /** Database column direccion SqlType(VARCHAR), Length(255,true), Default(None) */
    val direccion: Rep[Option[String]] = column[Option[String]]("direccion", O.Length(255,varying=true), O.Default(None))
    /** Database column Image SqlType(LONGBLOB), Default(None) */
    val image: Rep[Option[java.sql.Blob]] = column[Option[java.sql.Blob]]("Image", O.Default(None))
  }
  /** Collection-like TableQuery object for table Personas */
  lazy val Personas = new TableQuery(tag => new Personas(tag))

  /** Entity class storing rows of table Productos
   *  @param idProducto Database column id_producto SqlType(INT), AutoInc, PrimaryKey
   *  @param nombre Database column nombre SqlType(VARCHAR), Length(255,true)
   *  @param descripcion Database column descripcion SqlType(VARCHAR), Length(255,true)
   *  @param precio Database column precio SqlType(DOUBLE)
   *  @param unidades Database column unidades SqlType(INT)
   *  @param image Database column image SqlType(LONGBLOB), Default(None)
   *  @param dStartdate Database column d_startDate SqlType(DATETIME)
   *  @param dEnddate Database column d_endDate SqlType(DATETIME) */
  case class ProductosRow(idProducto: Int, nombre: String, descripcion: String, precio: Double, unidades: Int, image: Option[java.sql.Blob] = None, dStartdate: java.sql.Timestamp, dEnddate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ProductosRow objects using plain SQL queries */
  implicit def GetResultProductosRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Double], e3: GR[Option[java.sql.Blob]], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[ProductosRow] = GR{
    prs => import prs._
    ProductosRow.tupled((<<[Int], <<[String], <<[String], <<[Double], <<[Int], <<?[java.sql.Blob], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Productos. Objects of this class serve as prototypes for rows in queries. */
  class Productos(_tableTag: Tag) extends Table[ProductosRow](_tableTag, "Productos") {
    def * = (idProducto, nombre, descripcion, precio, unidades, image, dStartdate, dEnddate) <> (ProductosRow.tupled, ProductosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idProducto), Rep.Some(nombre), Rep.Some(descripcion), Rep.Some(precio), Rep.Some(unidades), image, Rep.Some(dStartdate), dEnddate).shaped.<>({r=>import r._; _1.map(_=> ProductosRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7.get, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_producto SqlType(INT), AutoInc, PrimaryKey */
    val idProducto: Rep[Int] = column[Int]("id_producto", O.AutoInc, O.PrimaryKey)
    /** Database column nombre SqlType(VARCHAR), Length(255,true) */
    val nombre: Rep[String] = column[String]("nombre", O.Length(255,varying=true))
    /** Database column descripcion SqlType(VARCHAR), Length(255,true) */
    val descripcion: Rep[String] = column[String]("descripcion", O.Length(255,varying=true))
    /** Database column precio SqlType(DOUBLE) */
    val precio: Rep[Double] = column[Double]("precio")
    /** Database column unidades SqlType(INT) */
    val unidades: Rep[Int] = column[Int]("unidades")
    /** Database column image SqlType(LONGBLOB), Default(None) */
    val image: Rep[Option[java.sql.Blob]] = column[Option[java.sql.Blob]]("image", O.Default(None))
    /** Database column d_startDate SqlType(DATETIME) */
    val dStartdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("d_startDate")
    /** Database column d_endDate SqlType(DATETIME) */
    val dEnddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("d_endDate")
  }
  /** Collection-like TableQuery object for table Productos */
  lazy val Productos = new TableQuery(tag => new Productos(tag))

  /** Entity class storing rows of table ProductosDepartamentos
   *  @param idProductoDepartamento Database column id_producto_departamento SqlType(INT), AutoInc, PrimaryKey
   *  @param idProducto Database column id_producto SqlType(INT)
   *  @param idDepartamento Database column id_departamento SqlType(INT)
   *  @param dStartdate Database column d_startDate SqlType(DATETIME)
   *  @param dEnddate Database column d_endDate SqlType(DATETIME) */
  case class ProductosDepartamentosRow(idProductoDepartamento: Int, idProducto: Int, idDepartamento: Int, dStartdate: java.sql.Timestamp, dEnddate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ProductosDepartamentosRow objects using plain SQL queries */
  implicit def GetResultProductosDepartamentosRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[java.sql.Timestamp]]): GR[ProductosDepartamentosRow] = GR{
    prs => import prs._
    ProductosDepartamentosRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Productos_Departamentos. Objects of this class serve as prototypes for rows in queries. */
  class ProductosDepartamentos(_tableTag: Tag) extends Table[ProductosDepartamentosRow](_tableTag, "Productos_Departamentos") {
    def * = (idProductoDepartamento, idProducto, idDepartamento, dStartdate, dEnddate) <> (ProductosDepartamentosRow.tupled, ProductosDepartamentosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idProductoDepartamento), Rep.Some(idProducto), Rep.Some(idDepartamento), Rep.Some(dStartdate), dEnddate).shaped.<>({r=>import r._; _1.map(_=> ProductosDepartamentosRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_producto_departamento SqlType(INT), AutoInc, PrimaryKey */
    val idProductoDepartamento: Rep[Int] = column[Int]("id_producto_departamento", O.AutoInc, O.PrimaryKey)
    /** Database column id_producto SqlType(INT) */
    val idProducto: Rep[Int] = column[Int]("id_producto")
    /** Database column id_departamento SqlType(INT) */
    val idDepartamento: Rep[Int] = column[Int]("id_departamento")
    /** Database column d_startDate SqlType(DATETIME) */
    val dStartdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("d_startDate")
    /** Database column d_endDate SqlType(DATETIME) */
    val dEnddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("d_endDate")

    /** Foreign key referencing Departamentos (database name Productos_Departamentos_ibfk_2) */
    lazy val departamentosFk = foreignKey("Productos_Departamentos_ibfk_2", idDepartamento, Departamentos)(r => r.idDepartamento, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Productos (database name Productos_Departamentos_ibfk_1) */
    lazy val productosFk = foreignKey("Productos_Departamentos_ibfk_1", idProducto, Productos)(r => r.idProducto, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ProductosDepartamentos */
  lazy val ProductosDepartamentos = new TableQuery(tag => new ProductosDepartamentos(tag))

  /** Entity class storing rows of table Proveedores
   *  @param idProveedor Database column id_proveedor SqlType(INT), AutoInc, PrimaryKey
   *  @param nombre Database column nombre SqlType(VARCHAR), Length(255,true)
   *  @param telefono Database column telefono SqlType(VARCHAR), Length(255,true)
   *  @param direccion Database column direccion SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param email Database column email SqlType(VARCHAR), Length(255,true)
   *  @param dStartdate Database column d_startDate SqlType(DATETIME)
   *  @param dEnddate Database column d_endDate SqlType(DATETIME) */
  case class ProveedoresRow(idProveedor: Int, nombre: String, telefono: String, direccion: Option[String] = None, email: String, dStartdate: java.sql.Timestamp, dEnddate: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ProveedoresRow objects using plain SQL queries */
  implicit def GetResultProveedoresRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[ProveedoresRow] = GR{
    prs => import prs._
    ProveedoresRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table Proveedores. Objects of this class serve as prototypes for rows in queries. */
  class Proveedores(_tableTag: Tag) extends Table[ProveedoresRow](_tableTag, "Proveedores") {
    def * = (idProveedor, nombre, telefono, direccion, email, dStartdate, dEnddate) <> (ProveedoresRow.tupled, ProveedoresRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idProveedor), Rep.Some(nombre), Rep.Some(telefono), direccion, Rep.Some(email), Rep.Some(dStartdate), dEnddate).shaped.<>({r=>import r._; _1.map(_=> ProveedoresRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_proveedor SqlType(INT), AutoInc, PrimaryKey */
    val idProveedor: Rep[Int] = column[Int]("id_proveedor", O.AutoInc, O.PrimaryKey)
    /** Database column nombre SqlType(VARCHAR), Length(255,true) */
    val nombre: Rep[String] = column[String]("nombre", O.Length(255,varying=true))
    /** Database column telefono SqlType(VARCHAR), Length(255,true) */
    val telefono: Rep[String] = column[String]("telefono", O.Length(255,varying=true))
    /** Database column direccion SqlType(VARCHAR), Length(255,true), Default(None) */
    val direccion: Rep[Option[String]] = column[Option[String]]("direccion", O.Length(255,varying=true), O.Default(None))
    /** Database column email SqlType(VARCHAR), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column d_startDate SqlType(DATETIME) */
    val dStartdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("d_startDate")
    /** Database column d_endDate SqlType(DATETIME) */
    val dEnddate: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("d_endDate")
  }
  /** Collection-like TableQuery object for table Proveedores */
  lazy val Proveedores = new TableQuery(tag => new Proveedores(tag))
}
