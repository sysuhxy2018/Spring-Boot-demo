package project.eureka.type0.db1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import project.eureka.type0.bean.Player;
import project.eureka.type0.db1.service.PlayerService;


@RestController
@RequestMapping("/player")	//controller处理.../user的请求，...代表域名
public class PlayerController {
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/query")	//这个方法会处理.../user/query的请求
	public ResponseEntity<List<Player>> testQuery(@RequestParam("name") String name) {
		return ResponseEntity.ok(playerService.findPlayersByName(name));
	}
	
	@GetMapping("/pager/{pageNum}/{pageSize}")
    public PageInfo<Player> selectByPage(@PathVariable int pageNum, @PathVariable int pageSize) {
        PageHelper.startPage(pageNum, pageSize); //这里初始化分页方式后，后面会自动拦截数据层操作，并修改SQL语句。不需要我们自己写limit
        List<Player> list = playerService.findAllPlayers();	//这里的结果已经是分好页的
        PageInfo<Player> pageInfo = new PageInfo<>(list);	//PageInfo会将页码等信息和list结果一起封装
        return pageInfo;
    }
}
