package servlet;

import Dao.Flower;
import service.FlowerService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FlowerManagementServlet")
public class FlowerManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam!= null &&!pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
            }
        }

        int pageSize = 10;

        FlowerService flowerService = new FlowerService();
        List<Flower> flowerList = flowerService.getAllFlowers(page, pageSize);

        int totalFlowers = flowerService.getTotalFlowers();


        request.setAttribute("flowerList", flowerList);
        request.setAttribute("totalFlowers", totalFlowers);
        request.setAttribute("currentPage", page);


        int totalPages = (int) Math.ceil((double) totalFlowers / pageSize);
        request.setAttribute("totalPages", totalPages);


        request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if ("addFlower".equals(operation)) {

            handleAddFlower(request, response);
        } else {

            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }

    private void handleAddFlower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flowerName = request.getParameter("flower_name");
        String flowerPrice = request.getParameter("flower_price");
        String flowerContent = request.getParameter("flower_content");
        String stock = request.getParameter("stock");

        Flower newFlower = new Flower();
        try {
            newFlower.setFlower_name(flowerName);
            newFlower.setFlower_price(Double.parseDouble(flowerPrice));
            newFlower.setFlower_content(flowerContent);
            newFlower.setStock(Integer.parseInt(stock));
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
            return;
        }

        FlowerService flowerService = new FlowerService();
        boolean success = flowerService.addFlower(newFlower);

        if (success) {

            response.sendRedirect("FlowerManagementServlet");
        } else {
            request.setAttribute("errorMessage", "fail");
            request.getRequestDispatcher("flowerManagement.jsp").forward(request, response);
        }
    }
}