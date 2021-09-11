package com.apt.proptech;

import com.apt.proptech.core.config.TableColumnConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
