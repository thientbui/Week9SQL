package projects.dao;

import java.math.BigDecimal; 
import java.sql.SQLException;
import java. sql.Connection; 
import java.sql.PreparedStatement; 
import projects.entity.Project;
import projects.exception.DbException; 
import provided.util.DaoBase; 

/* 
 * This class uses JDBC to perform CRUD operations on the project tables. 
 */

public class ProjectDao extends DaoBase{
	
	private static final String CATEGORY_TABLE = "category"; 
	private static final String MATERIAL_TABLE = "material"; 
	private static final String PROJECT_TABLE = "project"; 
	private static final String PROJECT_CATEGORY_TABLE = "project_category"; 
	private static final String STEP_TABLE = "step"; 
	
	/* 
	 * Insert a project row into the project table. 
	 * 
	 * @param project The project object to insert. 
	 * @return The Project object with the primary key. 
	 * @throws DbException Thrown if an error occurs inserting the row. 
	 * 
	 */

	public Project insertProject(Project project) throws SQLException {
		//@formatter:off 
		String sql = ""
		+ "INSERT INTO " + PROJECT_TABLE + "" 
		+"(project_name,estimated_hours, actual hours, difficulty, notes)"
		+ "VALUES "
		+"(?,?,?,?,?)"; 
		//formatter:on
		
	try(Connection conn = DbConnection.getConnection()) { 
		startTransaction(conn); 
		
	/*	try(PreparedStatement stmt = conn.prepareStatement(sql)){
			setParameter(stmt, 1, project.getProjectName(), String.class); 
			setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class); 
			setParameter(stmt, 3, project.getActualHours(), BigDecimal.class); 
			setParameter(stmt, 4, project.getDifficulty(), Integer.class); 
			setParameter(stmt, 5, project.getNotes(), String.class); 
			
	*/ 
		
		PreparedStatement stmt;
		stmt.executeUpdate(); 
		
		Integer projectId = getLastInsertId(conn, PROJECT_TABLE); 
		startTransaction(conn);
		
		project.setProjecId(projectId); 
		return project; 
		}
		catch(Exception e) { 
			rollbackTransaction(conn); 
			throw new DbException(e);
		
	}
}
	 catch(SQLException e) 
	 throw new DbException(e); 
		 
	 	}	
	}
}

		
