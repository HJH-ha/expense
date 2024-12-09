package mysite.expense.controller;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import mysite.expense.dto.ExpenseDTO;
import mysite.expense.dto.ExpenseFilterDTO;
import mysite.expense.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpenseFilterController {

    private final ExpenseService expService;


    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDTO expenseFilterDTO,
                                 Model model) {
         System.out.println(expenseFilterDTO);
        List<ExpenseDTO> list = expService.getFilterExpenses(expenseFilterDTO.getKeyword(),expenseFilterDTO.getSortBy());
        model.addAttribute("expenses", list);
        return "e_list";
    }
}
