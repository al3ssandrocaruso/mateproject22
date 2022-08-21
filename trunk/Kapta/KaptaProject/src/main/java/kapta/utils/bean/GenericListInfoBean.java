package kapta.utils.bean;

public class GenericListInfoBean {
    private int size ;
    private int maxsize;
    private int type;// 0 ==> Following , 1 ==> Follower , // 2 ===> created

    public GenericListInfoBean(int size , int type){
        setType(type);
        setSize(size);
    }


    public void setSize(int size) {
        this.size = size;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMaxsize(int maxsize) {
        this.maxsize = maxsize;
    }

    public int getSize() {
        return size;
    }

    public int getMaxsize() {
        return maxsize;
    }

    public int getType() {
        return type;
    }
}
