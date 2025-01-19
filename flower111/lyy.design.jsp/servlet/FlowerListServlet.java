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

@WebServlet("/FlowerListServlet")
public class FlowerListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int page = 1;
        int pageSize = 10;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // 如果页码参数不是数字，使用默认值1
        }

        FlowerService flowerService = new FlowerService();

        if (keyword!= null &&!keyword.isEmpty()) {
            // 执行模糊查询
            List<Flower> flowerList = flowerService.searchFlowers(keyword);
            request.setAttribute("flowerList", flowerList);
            request.setAttribute("keyword", keyword);
        } else {
            // 执行分页查询
            List<Flower> flowerList = flowerService.getAllFlowers(page, pageSize);
            int totalFlowers = flowerService.getTotalFlowers();
            int totalPages = (int) Math.ceil((double) totalFlowers / pageSize);

            request.setAttribute("flowerList", flowerList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        }

        request.getRequestDispatcher("flowerList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}