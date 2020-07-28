/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author cauch
 */
public class Group extends BaseModel{
    private String name;
    private ArrayList<Functionality> funtions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Functionality> getFuntions() {
        return funtions;
    }

    public void setFuntions(ArrayList<Functionality> funtions) {
        this.funtions = funtions;
    }

   
}
