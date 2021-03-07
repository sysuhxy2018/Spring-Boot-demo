package project.eureka.type0.db1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.eureka.type0.bean.Player;
import project.eureka.type0.db1.dao.PlayerDao;

@Service
public class PlayerService {
	@Autowired
	private PlayerDao playerDao;
	
	public List<Player> findPlayersByName(String name) {
		return playerDao.findPlayersByName(name);
	}
	
	public List<Player> findAllPlayers() {
		return playerDao.findAllPlayers();
	}
}
