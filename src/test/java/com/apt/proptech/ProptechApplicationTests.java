package com.apt.proptech;

import com.apt.proptech.config.TableColumnConfig;
import com.apt.proptech.domain.ClaimProp;
import com.apt.proptech.domain.SaleProp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class ProptechApplicationTests {

    @Autowired
    private TableColumnConfig tableConfig;

    @Test
    void tableConfig() {

        for(String t1 : tableConfig.getUserColumn()){
            System.out.println("UserCol : " +t1 );
        }

        for(String t2 : tableConfig.getAssociateColumn()){
            System.out.println("AssociateCol : " +t2 );
        }


    }

}
