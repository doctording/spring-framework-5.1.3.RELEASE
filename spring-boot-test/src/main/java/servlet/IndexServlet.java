package servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author mubi
 * @Date 2020/7/25 21:05
 */
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Log logger = LogFactory.getLog(IndexServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		logger.info("-------IndexServlet");
	}
}
