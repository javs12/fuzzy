package com.example.fuzzysearch.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.example.fuzzysearch.domain.Person;
import com.example.fuzzysearch.domain.Search;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InitHelper implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4592478294109525070L;

  public InitHelper() {
    super();
  }

  public String addPerson(String json) {

    BufferedWriter bw = null;
    FileWriter fw = null;
    try {

      ObjectMapper om = new ObjectMapper();
      Person person = (om.readValue(json, Person.class));
      File file = new File("fuzzy-search.txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      fw = new FileWriter(file.getAbsoluteFile(), true);
      bw = new BufferedWriter(fw);
      bw.write(person.getName());
      bw.write('\n');

    } catch (Exception e) {
      return e.getMessage();
    } finally {
      try {
        if (bw != null)
          bw.close();
        if (fw != null)
          fw.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }

    }

    return "Usuario Agregado";
  }

  public List<Person> getList() {
    return this.readFile().stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
  }

  public Object searchByName(String json) {

    Person person = new Person();
    try {

      ObjectMapper om = new ObjectMapper();
      Search search = (om.readValue(json, Search.class));
      List<Person> list = this.readFile();
      List<Double> nums = new ArrayList<>();
      Double aux = 0.0;
      for (Person p : list) {
        Double num = this.findMatch(search.getSearch().toLowerCase(), p.getName().toLowerCase());
        nums.add(num);
        if (num != 0.0) {

          if (aux < num) {

            person.setName(p.getName());
            aux = num;

          }

        }
      }

      if (person.getName() == null) {

        return "Sin coincidencias";

      }

    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return person;
  }

  private Double findMatch(String search, String name) {

    List<char[]> s = this.bigram(search);
    List<char[]> n = this.bigram(name);

    return this.dice(s, n);

  }

  private List<char[]> bigram(String input) {
    ArrayList<char[]> bigram = new ArrayList<char[]>();
    for (int i = 0; i < input.length() - 1; i++) {
      char[] chars = new char[2];
      chars[0] = input.charAt(i);
      chars[1] = input.charAt(i + 1);
      bigram.add(chars);
    }
    return bigram;
  }

  private double dice(List<char[]> bigram1, List<char[]> bigram2) {
    List<char[]> copy = new ArrayList<char[]>(bigram2);
    int matches = 0;
    for (int i = bigram1.size(); --i >= 0;) {
      char[] bigram = bigram1.get(i);
      for (int j = copy.size(); --j >= 0;) {
        char[] toMatch = copy.get(j);
        if (bigram[0] == toMatch[0] && bigram[1] == toMatch[1]) {
          copy.remove(j);
          matches += 2;
          break;
        }
      }
    }
    return (double) matches / (bigram1.size() + bigram2.size());
  }

  private List<Person> readFile() {

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    List<Person> personList = new ArrayList<>();

    try {
      archivo = new File("fuzzy-search.txt");
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null) {
        Person person = new Person();
        person.setName(linea);
        personList.add(person);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != fr) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }

    return personList;
  }

}
