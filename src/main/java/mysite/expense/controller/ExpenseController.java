package mysite.expense.controller;

import lombok.RequiredArgsConstructor;
import mysite.expense.dto.ExpenseDTO;
import mysite.expense.dto.ExpenseFilterDTO;
import mysite.expense.entity.Expense;
import mysite.expense.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;


@Controller
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expService;

    @GetMapping("/expenses")
    public String showList(Model model) {
        model.addAttribute("expenses", expService.getAllExpenses());
        model.addAttribute("filter", new ExpenseFilterDTO());
        return "e_list";
    }
    
    //Get 요청시 비용 임력을 위한 창을 보여주기
    @GetMapping("/createExpense")
    public String showCreateForm(Model model) {
        model.addAttribute("expense", new ExpenseDTO()); // 빈 expense 객체 전달
        return "e_form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
        //System.out.println("입력한 expenseDTO 객체 : " + expenseDTO);
        expService.saveExpenseDetails(expenseDTO);

        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam("id") String expenseId) {
        //System.out.println("삭제 비용 번호 : " + expenseId);
        expService.deleteExpense(expenseId);
        return "redirect:/expenses";
    }
    
    // 수정할 페이지 보여주기
    @GetMapping("/updateExpense")
    public String updateExpense(@RequestParam("id") String expenseId, Model model) {
        //System.out.println("업데이트 아이템 : " + expenseId);
        // DB 에서 해당 id의 객체를 전달하여 수정 할 수 있게함.
        ExpenseDTO expenseDTO = expService.getExpenseById(expenseId);
        //System.out.println(expenseDTO);
        model.addAttribute("expense", expenseDTO);
        return "e_form";
    }

}
