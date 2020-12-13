package com.esteelauder.top.service;

import com.esteelauder.top.example.ActressExample;
import com.esteelauder.top.mapper.ActressMapper;
import com.esteelauder.top.model.Actress;
import com.esteelauder.top.parameter.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author 墨茗琦妙
 */
@Service
public class ActressService {

    @Autowired
    private ActressMapper actressMapper;

    public Collection<Actress> all() {
        ActressExample example = new ActressExample();
        example.setOrderByClause("rank asc");
        return actressMapper.selectByExample(example);
    }

    public void up(Params param) {
        Actress actress = actressMapper.selectById(param.getId());
        Integer rank = actress.getRank();

        switch (param.getType()) {
            //up
            case 1:
                Actress previousActress = actressMapper.previous(actress.getRank());
                if (Objects.nonNull(previousActress)) {
                    actress.setRank(previousActress.getRank());
                    previousActress.setRank(rank);
                    actressMapper.updateByPrimaryKey(previousActress);
                    actressMapper.updateByPrimaryKey(actress);
                }
                break;
                //down
            case 2:
                Actress next = actressMapper.next(rank);
                if (Objects.nonNull(next)) {
                    actress.setRank(next.getRank());
                    next.setRank(rank);
                    actressMapper.updateByPrimaryKey(next);
                    actressMapper.updateByPrimaryKey(actress);
                }
                break;
                //top
            case 3:
                ActressExample example = new ActressExample();
                ActressExample.Criteria criteria = example.createCriteria();
                criteria.andIdNotEqualTo(param.getId()).andRankLessThan(rank);
                List<Actress> actresses = actressMapper.selectByExample(example);
                Integer min = actressMapper.minRank();
                actress.setRank(min);
                actressMapper.updateByPrimaryKey(actress);
                this.toppingOrBottom(actresses,3);
                break;
            case 4:
                ActressExample example1 = new ActressExample();
                ActressExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andIdNotEqualTo(param.getId()).andRankGreaterThan(rank);
                List<Actress> actresses1 = actressMapper.selectByExample(example1);
                Integer max = actressMapper.maxRank();
                actress.setRank(max);
                actressMapper.updateByPrimaryKey(actress);
                this.toppingOrBottom(actresses1,4);
                break;
            default:;
        }

    }

    @Async
    public void toppingOrBottom(List<Actress> actresses ,Integer type){
        if (type == 3){
            actresses.forEach(actress ->{
                actress.setRank(actress.getRank()+1);
                actressMapper.updateByPrimaryKey(actress);
            });
            //bottom
        }else {
            actresses.forEach(actress ->{
                actress.setRank(actress.getRank()-1);
                actressMapper.updateByPrimaryKey(actress);
            });
        }

    }


}
