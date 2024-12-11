package mysite.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseFilterDTO {
    // 검색어
    private String keyword;
    
    // 정렬, 순서
    private String sortBy;

    //시작일
    private String startDate;

    //종료일
    private String endDate;
    
    // 한달 시작일과 현재일을 미리 입력하기 위한 생성자
    // 이번달 첫일과 오늘까지 날짜를 문자열로 넣은 생성자
    public ExpenseFilterDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
