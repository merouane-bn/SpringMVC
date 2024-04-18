package ma.emsi.hopital3.web;

import lombok.AllArgsConstructor;
import ma.emsi.hopital3.entities.Patient;
import ma.emsi.hopital3.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

//    public PatientController(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
//methode qui retourne une vue patients.html
@GetMapping( "/index")
public String index(Model model,
                    @RequestParam(name = "page",defaultValue = "0") int page,
                    @RequestParam(name = "size",defaultValue = "4") int size,
                    @RequestParam(name = "keyword",defaultValue = "") String keyword){
//    Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
    model.addAttribute("listPatient", pagePatients.getContent());
    model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
    model.addAttribute("currentPage", page);
    model.addAttribute("keyword", keyword);
    return "patients";
   }

    @GetMapping("/delete")
    public String delete(@RequestParam(name="id") Long id,@RequestParam(name = "keyword",defaultValue = "")String keyword,
                         @RequestParam(name = "page",defaultValue = "0")int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/displayDetails")
    public String displayDetails(Long id, Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient", patient);
        return "patientDetails";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
}