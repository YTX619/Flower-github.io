package servlet;

import Dao.Flower;
import Dao.FlowerDAO;
import Dao.impl.FlowerDAOImpl;
import service.FlowerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditFlowerServlet")
public class EditFlowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get flower id
        int flowerId = Integer.parseInt(request.getParameter("flowerId"));

        // search flower by id
        FlowerDAO flowerDAO = new FlowerDAOImpl();
        Flower flower = flowerDAO.getFlowerById(flowerId);

        // set flower
        request.setAttribute("flower", flower);

        // to editflower
        request.getRequestDispatcher("editFlower.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flowerId=request.getParameter("flowerId");
        String flowerName = request.getParameter("flowerName");
        String flowerPrice = request.getParameter("flowerPrice");
        String flowerContent = request.getParameter("flowerContent");
        String stock = request.getParameter("stock");

        Flower newFlower = new Flower();
        try {
            newFlower.setFlower_id(Integer.parseInt(flowerId));
            newFlower.setFlower_name(flowerName);
            newFlower.setFlower_price(Double.parseDouble(flowerPrice));
            newFlower.setFlower_content(flowerContent);
            newFlower.setStock(Integer.parseInt(stock));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
            return;
        }

        FlowerDAO flowerDAO = new FlowerDAOImpl();
        boolean success = flowerDAO.updateFlower(newFlower);

        if (success) {
            request.setAttribute("message", "success");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }
}