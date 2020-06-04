package servlet

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Admin
import model.Collage
import model.FullTarget
import model.FullTarget.Level.*
import model.SQL
import util.Router
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.MultipartConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author wcf
 */
@WebServlet(name = "controller", urlPatterns = ["/action/*"])
class Controller : HttpServlet(), Router {

    override lateinit var context: String
    override val get = HashMap<String, (HttpServletRequest, HttpServletResponse) -> Unit>()
    override val post = HashMap<String, (HttpServletRequest, HttpServletResponse) -> Unit>()

    override fun init() {
        context = servletContext.contextPath

        get("/action") { _, resp ->
            resp.writer.println("hello world")
        }

        get("/action/hello") { req, resp ->
            val name = req.fields()["name"]
            resp.writer.println("hello world, $name")
        }

        post("/action/upload/database") { req, resp ->
            req.getRequestDispatcher("/uploadDatabase").forward(req, resp)
        }

        request("/action/login/admin") { _, resp ->
            resp.sendRedirect("$context/login.jsp?user=admin")
        }

        request("/action/punch_record") { req, resp ->
            resp.contentType = "application/json;charset=UTF-8"
            val admin = req.session.getAttribute("admin")
            if (admin !is Admin || admin == Admin()) {
                println("admin不存在")
                resp.writer.write(gson.toJson(ArrayList<PunchRecordData.OverView>()))
            } else {
                val fullTarget = admin.fullTarget
                when (fullTarget.level) {
                    SYSTEM, SCHOOL -> {
                        val field = req.fields()
                        val collage = field["collage"]?.toInt()
                        if (collage != null) {
                            resp.writer.write(gson.toJson(PunchRecordData.overViewData(Collage(collage, ""))))
                        } else {
                            resp.writer.write(gson.toJson(PunchRecordData.overViewDataAll()))
                        }
                    }
                    COLLAGE -> {
                        val target = fullTarget.target
                        if (target != null) {
                            resp.writer.write(gson.toJson(PunchRecordData.overViewData(target)))
                        } else {
                            resp.writer.write(gson.toJson(ArrayList<PunchRecordData.OverView>()))
                        }
                    }
                    NULL -> {
                        if (fullTarget.healthCode) {
                            TODO("仅有健康码权限")
                        } else {
                            TODO("什么权限也没有")
                        }
                    }
                }
            }
        }

        request("/action/punch_record/avail_list") { req, resp ->
            resp.contentType = "application/json;charset=UTF-8"
            val admin = req.session.getAttribute("admin")
            if (admin !is Admin || admin == Admin()) {
                println("admin不存在")
                resp.writer.write(gson.toJson(ArrayList<PunchRecordData.OverView>()))
            } else {
                resp.writer.write(gson.toJson(PunchRecordData.availableCollage(admin)))
            }
        }
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.contentType = "text/html;charset=UTF-8"
        val uri = request.requestURI
        post[uri]?.invoke(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.contentType = "text/html;charset=UTF-8"
        val uri = request.requestURI
        get[uri]?.invoke(request, response)

        //region sample
//        val teacher = SQL().query(Teacher::class.java, "id" to "00001")
//        val student = SQL().query(Student::class.java, Pair("id", "201806061108"))
//        val admin = SQL().query(Admin::class.java, Pair("id", "000000"))
//        val major = SQL().query(Major::class.java, Pair("name", "计算机科学与技术"))
//        val collage = SQL().query(Collage::class.java, Pair("id", 580))
//        val clazz = SQL().query(Clazz::class.java, Pair("id", 1055))
//        val healthInfo = SQL().query(HealthInfo::class.java, "uid" to "201806061219")
//        val punchRecord = SQL().queryList(PunchRecord::class.java, "uid" to "201806061219")
//        val collages = SQL().queryList(Collage::class.java)
//        println(MessageFormat.format(
//                "teacher[000001]:{0}\n\nstudent[201806061108]:{1}\n\nadmin[1]:{2}\n\nmajor[1]:{3}\n\ncollage[1]:{4}\n\nclass[5]:{5}\n\nhealth_info[201806061219]:{6}\n\npunch_record[201806061219]:{7}\n\ncollages:{8}\n\n",
//                gson.toJson(teacher),
//                gson.toJson(student),
//                gson.toJson(admin),
//                gson.toJson(major),
//                gson.toJson(collage),
//                gson.toJson(clazz),
//                gson.toJson(healthInfo),
//                gson.toJson(punchRecord),
//                gson.toJson(collages)
//        ))
        //endregion
    }

    companion object {
        val gson = GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().serializeNulls().create()
    }
}