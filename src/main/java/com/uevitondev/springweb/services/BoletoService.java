package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagB, Date instant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagB.setDataPagamento(cal.getTime());
    }
}
