package coffee.shop.service;

import coffee.shop.constant.MessageConstants;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class CategoryValidator {
    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            log.warn(MessageConstants.CATEGORY_NAME_INVALID);
            throw new IllegalArgumentException(MessageConstants.CATEGORY_NAME_INVALID);
        }
    }
}
