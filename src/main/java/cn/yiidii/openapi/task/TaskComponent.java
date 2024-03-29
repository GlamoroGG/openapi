package cn.yiidii.openapi.task;

import cn.yiidii.openapi.task.service.IDuoDianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: YiiDii Wang
 * @create: 2020-12-27 23:23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TaskComponent {

    private final IDuoDianService duoDianService;

    @Async("scheduleTaskExecutor")
//    @Scheduled(fixedDelay = 10 * 1000)
    @Scheduled(cron = "0 1 0 * * ?")
    public void duodianChenkIn() {
        log.info("开始多点签到");
        Map<String, String> mobileMap = new HashMap<>();
        mobileMap.put("18231590075", "Hm_lvt_3c760cfd485a098a377d76ceec857d7d=1609069247; Hm_lpvt_3c760cfd485a098a377d76ceec857d7d=1609069268; tempid=C930CBF93C000002BC7BDA506FE0EBD0; session_id=C930CBF93C000002EADB109018B04BE0; platform=webApp; first_session_time=1609070068427; web_session_count=1; updateTime=1609070069574; inited=true; hasBind=true; ticketLoginId=3b355e94-f451-4ca2-bd74-7751e7f08be4; ticketName=79532D186D54E18F39C6035BE0ABD7CC3A64E3665EF45283FC3F06FADB7D53CB42201F27854B3BA1397AC96B37EE44DA0947A440B5F0E64B57C0E6012D1E903DD3C4BBA9E036BD5D206A8969C88F4E4B5D36B134221C8B81541E0E3A9EEF4BE0F515DC4B05093F85B37460E81A7319F8BDB08264E1C27DA023AA3FCBA7E0B79D; ticketWeChat=79532D186D54E18F39C6035BE0ABD7CC3A64E3665EF45283FC3F06FADB7D53CB42201F27854B3BA1397AC96B37EE44DA0947A440B5F0E64B57C0E6012D1E903DD3C4BBA9E036BD5D206A8969C88F4E4B5D36B134221C8B81541E0E3A9EEF4BE0F515DC4B05093F85B37460E81A7319F8BDB08264E1C27DA023AA3FCBA7E0B79D; token=105b632d-6afd-4d18-b292-a6da13421bb6; data_seq=8");
        mobileMap.put("15822427860", "tempid=C930D928069000027693A47EBC201379; session_id=C930D92806A00002E5D6D4B016D71EF3; platform=webApp; first_session_time=1609083889966; web_session_count=1; updateTime=1609083890956; inited=true; hasBind=true; ticketLoginId=a1e80dd7-dca0-470b-8122-966bdc971397; ticketName=5340BD35FCB88EF4AA517000A72065C240A7E1C5AF8F53A26BF5C84E50360F31AE35523B5EF1FA4D65FC0DBD7F99231E51AFD380C3BC6AE80CE2D66EA1D66438A9CD4600AD19AAD8DCDB30169C88E91F454B9ED16A1BA328AAD742C4339D33359217F177F7C23E595985A87F5B9C306B43A49926746E0AFD8669539882DD467F; ticketWeChat=5340BD35FCB88EF4AA517000A72065C240A7E1C5AF8F53A26BF5C84E50360F31AE35523B5EF1FA4D65FC0DBD7F99231E51AFD380C3BC6AE80CE2D66EA1D66438A9CD4600AD19AAD8DCDB30169C88E91F454B9ED16A1BA328AAD742C4339D33359217F177F7C23E595985A87F5B9C306B43A49926746E0AFD8669539882DD467F; token=8abf9eed-0d60-4d49-823a-a433f624fbd0; data_seq=5");
        for (Map.Entry<String, String> entry : mobileMap.entrySet()) {
            String mobile = entry.getKey();
            String ck = entry.getValue();
            try {
                duoDianService.checkIn(mobile, ck);
            } catch (Exception e) {
                log.error("多点定时任务异常: {}", e.getMessage());
            }
        }
        log.info("多点签到执行完成");
    }

}
