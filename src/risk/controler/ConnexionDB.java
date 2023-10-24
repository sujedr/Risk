package risk.controler;
import java.sql.*;

public class ConnexionDB {
	public static void main(String[] args) {
        Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // connexion
            String url = "jdbc:mysql://localhost:3306/risk";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);

         // requête
            String sql = "SELECT * FROM joueur";

            // execution
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // afficher résultats
            while (resultSet.next()) {
                String nom = resultSet.getString("vNomJoueur");
                String prenom = resultSet.getString("vPrenomJoueur");
                System.out.println("Nom : " + nom + ", Prenom : " + prenom);
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du pilote : " + e.getMessage());

        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

}
