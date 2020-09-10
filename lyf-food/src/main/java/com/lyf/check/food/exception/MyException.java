package com.lyf.check.food.exception;

import com.lyf.common.codeMesg.MyCodemsg;
import com.lyf.common.exception.MyEsException;
import com.lyf.common.exception.RRException;
import com.lyf.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyf
 * @date 2020/6/11-17:00
 */
@Slf4j
@RestControllerAdvice
public class MyException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R getExc(MethodArgumentNotValidException e) {
        log.error("实体校验异常" + e.getClass());
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        result.getFieldErrors().forEach((item) -> {
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field, message);
        });
        return R.error(MyCodemsg.VOLATILE_EXCEPTION.getCode(), MyCodemsg.VOLATILE_EXCEPTION.getMsg()).put("data", map);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRRException(RRException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    /**
     * 处理专题保存es异常回滚
     */
    @ExceptionHandler(value = MyEsException.class)
    public R getEsEX(MyEsException e){
        log.error("es保存专题信息异常回滚" + e.getClass());
        return R.error(MyCodemsg.ES_EXCEPTION.getCode(),MyCodemsg.ES_EXCEPTION.getMsg());
    }
}
