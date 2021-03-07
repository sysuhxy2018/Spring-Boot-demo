package project.eureka.type0.db2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.eureka.type0.bean.Money;
import project.eureka.type0.db2.dao.MoneyDao;

@Service
public class MoneyService {
    @Autowired
    private MoneyDao moneyDao;

    public Money selectMoneyById(int id) {
        return moneyDao.findMoneyById(id);
    }

}