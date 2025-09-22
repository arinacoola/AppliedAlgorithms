import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.List;

public class PythonGraphClient {
    public static void sendGraphAndShowImage(List<String> edgeListJson, boolean directed, boolean weighted) {
        String json = buildJson(edgeListJson, directed, weighted);
        System.out.println("Sending JSON: " + json);
        try {
            URL url = new URL("http://localhost:5000/draw");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode == 200) {
                BufferedImage img = ImageIO.read(conn.getInputStream());
                showImage(img);
            }
            else {
                InputStream errorStream = conn.getErrorStream();
                if (errorStream != null) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    System.err.println("Server error response: " + response);
                    JOptionPane.showMessageDialog(null, "Error from server: " + responseCode + "\n" + response);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error from server: " + responseCode + " (no details)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
    }

    private static String buildJson(List<String> edges, boolean directed, boolean weighted) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"edges\":[");
        for (String edge : edges) {
            sb.append(edge).append(",");
        }
        if (!edges.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("],\"directed\":").append(directed).append(",\"weighted\":").append(weighted).append("}");
        return sb.toString();
    }

    private static void showImage(BufferedImage img) {
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }
}
