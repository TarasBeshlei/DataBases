package com.taras.service;

import com.taras.Repository.LoggerRepository;
import com.taras.domain.Logger;
import com.taras.exceptions.NoSuchLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerService {
    @Autowired
    LoggerRepository loggerRepository;

    public List<Logger> getAllLogger() {
        return loggerRepository.findAll();
    }

    public List<Logger> getLoggerFilterBySurname(String like) {
        return loggerRepository.findByStudentLike(like + "%");
    }

    public Logger getLog(Long log_id) throws NoSuchLogException {
//        Logger logger =loggerRepository.findOne(log_id);//1.5.9
        Logger logger = loggerRepository.findById(log_id).get();//2.0.0.M7
        if (logger == null) throw new NoSuchLogException();
        return logger;
    }
}
