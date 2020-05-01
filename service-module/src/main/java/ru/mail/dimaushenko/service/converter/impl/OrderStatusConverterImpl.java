package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.constants.OrderStatusEnum;
import ru.mail.dimaushenko.service.converter.OrderStatusConverter;
import ru.mail.dimaushenko.service.model.OrderStatusEnumDTO;

@Component
public class OrderStatusConverterImpl implements OrderStatusConverter {

    @Override
    public OrderStatusEnumDTO getDTOFromObject(OrderStatusEnum orderStatusEnum) {
        if (orderStatusEnum != null) {
            switch (orderStatusEnum) {
                case NEW:
                    return OrderStatusEnumDTO.NEW;
                case DELIVERED:
                    return OrderStatusEnumDTO.DELIVERED;
                case IN_PROGRESS:
                    return OrderStatusEnumDTO.IN_PROGRESS;
                case REJECTED:
                    return OrderStatusEnumDTO.REJECTED;
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public List<OrderStatusEnumDTO> getDTOFromObject(List<OrderStatusEnum> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderStatusEnum getObjectFromDTO(OrderStatusEnumDTO orderStatusEnumDTO) {
        if (orderStatusEnumDTO != null) {
            switch (orderStatusEnumDTO) {
                case DELIVERED:
                    return OrderStatusEnum.DELIVERED;
                case IN_PROGRESS:
                    return OrderStatusEnum.IN_PROGRESS;
                case NEW:
                    return OrderStatusEnum.NEW;
                case REJECTED:
                    return OrderStatusEnum.REJECTED;
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public List<OrderStatusEnum> getObjectFromDTO(List<OrderStatusEnumDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
