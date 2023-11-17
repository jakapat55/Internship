package tt4.sasbackend;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import tt4.sasbackend.DTO.pageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();

    private ListMapper() {
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }

    public static ListMapper getInstance() {
        return listMapper;
    }


    public <S, T> pageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass,
                                       ModelMapper modelMapper) {
        pageDTO<T> page = modelMapper.map(source, pageDTO.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }


}
