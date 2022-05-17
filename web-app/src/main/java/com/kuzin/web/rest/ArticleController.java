package com.kuzin.web.rest;



import com.kuzin.entity.Article;
import com.kuzin.entity.Repair;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.service.service.ArticleService;
import com.kuzin.web.exporter.FilterPdfExporter;
import com.kuzin.web.exporter.ReportPdfExporter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**article controller class.*/

@RestController
@RequestMapping("/articles/api")
public class ArticleController {
    ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Article> get() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable ("id") long id) throws AccessException {
        return service.get(id);
    }

    @PostMapping
    public void save(@ModelAttribute Article article, HttpServletResponse response)
            throws IOException {
        Article result = service.save(article);

        response.sendRedirect("/articles/get/" + result.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") long id) {
        int result = service.delete(id);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("article with id: " + id + " was deleted");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Article article,
                                         @PathVariable ("id") long id) {
        service.update(article, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("article with id: " + id + " was updated");
    }


    @GetMapping("/report/{id}")
    public List<WorksMaterial> getReport(@PathVariable ("id") long id) throws AccessException {
        return service.getReport(id);
    }

    @GetMapping("/report/export/{id}")
    public void export(@PathVariable ("id") long id, HttpServletResponse response)
            throws AccessException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<WorksMaterial> report = service.getReport(id);

        ReportPdfExporter exporter = new ReportPdfExporter(report);
        exporter.export(response, service.get(id).getValue());
    }


    @GetMapping("/description/{id}/{filter}")
    public List<Repair> getFilterByRepair(@PathVariable ("id") long id,
                                          @PathVariable ("filter") String filter)
            throws AccessException {
        return service.filerByDescription(id, filter);
    }

    @GetMapping("/repair/export/{id}/{filter}")
    public void exportRepair(@PathVariable ("id") long id,
                                          @PathVariable ("filter") String filter,
                                     HttpServletResponse response)
            throws AccessException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Repair> report = service.filerByDescription(id, filter);

        FilterPdfExporter exporter = new FilterPdfExporter(report);
        exporter.export(response);
    }

}
