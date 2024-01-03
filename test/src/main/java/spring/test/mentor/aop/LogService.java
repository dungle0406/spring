//package spring.test.mentor.aop;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class LogService {
//    private final LogRepository logRepository;
//    private final LogMapper logMapper;
//
//    @Autowired
//    public LogService(LogRepository logRepository, LogMapper logMapper) {
//        this.logRepository = logRepository;
//        this.logMapper = logMapper;
//    }
//
////    public void addNewLogMessages(RuntimeException logMessage) {
////        logRepository.save(logMapper.mapLogServiceFromRuntimeException(logMessage));
////    }
//
//    public List<LogMessage> getLogs() {
//        return logRepository.findAll();
//    }
//}
