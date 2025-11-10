package com.lms.LMS.repo;

import com.lms.LMS.model.Loan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class LoanRepo extends InFileRepo<Loan> {

    public LoanRepo(
            @Value("${app.data.directory:src/main/resources/data}") String dataDirectory
    ) {
        super(Loan.class, "loans.json", dataDirectory);
    }
}