package ru.youpromocodebot.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;

import static ru.youpromocodebot.util.EntityToDto.convertProgramToDto;

@Repository
@AllArgsConstructor
public class PartnershipsProgramsDaoImpl implements PartnershipsProgramsDao {

    private final CrudPartnershipsProgramRepository crudPartnershipsProgramRepository;

    @Override
    public List<ProgramToUser> findAll() {
        return convertProgramToDto(new Programs(crudPartnershipsProgramRepository.findAll()));
    }
}
