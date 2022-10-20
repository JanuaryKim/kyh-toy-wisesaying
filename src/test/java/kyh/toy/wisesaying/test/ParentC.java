package kyh.toy.wisesaying.test;

import java.util.List;

public class ParentC {

    List<Integer> list = List.of(1,2);

    protected void overFunc() {

        System.out.println(this.list.get(0));

    }
}
