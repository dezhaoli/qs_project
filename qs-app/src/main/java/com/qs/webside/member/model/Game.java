package com.qs.webside.member.model;

import java.io.Serializable;

public class Game extends GameKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -364640022426599014L;
	private Long jifen;

    public Long getJifen() {
        return jifen;
    }

    public void setJifen(Long jifen) {
        this.jifen = jifen;
    }
}