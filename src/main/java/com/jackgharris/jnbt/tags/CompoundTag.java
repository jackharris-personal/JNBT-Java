package com.jackgharris.jnbt.tags;

import com.jackgharris.jnbt.utils.ByteArray;
import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;
import com.jackgharris.jnbt.tags.arrays.ListTag;
import com.jackgharris.jnbt.tags.numbers.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * The compound tag can contain a sequential list of names or unnamed tags
 * it can also contain recursive compound tags with in its children and
 * can contain all tag types.
 */
public class CompoundTag extends Tag {

    protected final ArrayList<Tag> children;
    protected TagType type = TagType.COMPOUND;

    /**
     * @param key Accepting the key (name) for this compound
     *            tag the constructor will create a new instance.
     */
    public CompoundTag(String key) {
        super(key);
        this.children = new ArrayList<>();
    }

    /**
     * Creates a new instance of a compound tag.
     */
    public CompoundTag(){
        super("");
        this.children = new ArrayList<>();
    }

    /**
     * @param data Accepting data as an array of bytes this
     *             constructor will rebuild the compound tag
     *             object and children.
     */
    public CompoundTag(byte[] data){
        super("");

        this.children = new ArrayList<>();

        //To start extracting our compound array we first wrap our
        //bytes array into a ByteArray for easy manipulation.
        ByteArray bytes = new ByteArray(data);

        //Next we already know our tag type so we simply remove the
        //type byte.
        bytes.pop(0);

        //Next we get our key size, this is always the first two
        //bytes in the string.
        byte[] keySize = bytes.getSubSection(0,2);
        short keySizeShort = ByteBuffer.wrap(keySize).getShort();

        //Once we have the key size we remove the bytes specifying
        //it.
        bytes.pop(0,2);

        //next we check to ensure we have a key to extract, if so
        //we copy out the key bytes, convert them back to a string
        //and then remove the key bytes.
        if(keySizeShort != 0) {
            byte[] key = bytes.getSubSection(0, keySizeShort);
            this.key = new String(key);
            bytes.pop(0,keySizeShort);
        }

        //Finally we know that as this is a compound tag the next
        //four bytes are reserved for detailing the total size of
        //the compound payload, therefor we can remove them as they
        //are not required any more.
        bytes.pop(0,4);

        //Finally we start our extraction loop.
        this.runExtractionLoop(bytes);
    }

    /**
     * @param byteArray Accepts a byte array and extracts the next section
     *                  based on its starting byte id.
     * @return Returns remaining byte array after extracting the next
     * section.
     */
    private ByteArray extractNextSection(ByteArray byteArray){

        String keyString = "";
        byte[] keySize = byteArray.getSubSection(1,2);
        short keySizeShort = ByteBuffer.wrap(keySize).getShort();
        int popCount = 0;

        switch (byteArray.get(0)){

            case TAG_STRING -> {

                //Get value size;
                byte[] valueSize = byteArray.getSubSection(3+keySizeShort,2);
                short valueSizeShort = ByteBuffer.wrap(valueSize).getShort();

                byte[] data = byteArray.getSubSection(0,5+valueSizeShort+keySizeShort);

                this.children.add(new StringTag(data));

                popCount = 5+valueSizeShort+keySizeShort;
            }

            case TAG_BYTE -> {
                this.children.add(new ByteTag(byteArray.getSubSection(0,3+keySizeShort+1)));
                popCount = 3+keySizeShort+1;
            }

            case TAG_SHORT ->{
                this.children.add(new ShortTag(byteArray.getSubSection(0,3+keySizeShort+2)));
                popCount = 3+keySizeShort+2;
            }

            case TAG_INT ->{
                this.children.add(new IntegerTag(byteArray.getSubSection(0,3+keySizeShort+4)));
                popCount= 3+keySizeShort+4;
            }

            case TAG_FLOAT -> {
                this.children.add(new FloatTag(byteArray.getSubSection(0,3+keySizeShort+4)));
                popCount= 3+keySizeShort+4;
            }

            case TAG_LONG -> {
                this.children.add(new LongTag(byteArray.getSubSection(0,3+keySizeShort+8)));
                popCount= 3+keySizeShort+8;
            }

            case TAG_DOUBLE -> {
                this.children.add(new DoubleTag(byteArray.getSubSection(0,3+keySizeShort+8)));
                popCount= 3+keySizeShort+8;
            }

            case TAG_COMPOUND, TAG_LIST -> {

                byte[] length = byteArray.getSubSection(3+keySizeShort,4);
                int lengthInt = ByteBuffer.wrap(length).getInt();

                int size = 3+keySizeShort+4+lengthInt;

                //With compounds and lists we simply check what one
                //we are creating a child of and then generate the
                //correct object type.
                if(byteArray.get(0) == TAG_COMPOUND) {
                    this.children.add(new CompoundTag(byteArray.getSubSection(0, size)));
                }else{
                    this.children.add(new ListTag(byteArray.getSubSection(0, size)));
                }

                popCount = size;
            }

            default -> throw new IllegalStateException("Invalid TagId provided. : " + byteArray.get(0));
        }

        byteArray.pop(0,popCount);

        return byteArray;
    }

    /**
     * @param byteArray Internal loop method that extracts all the data
     *                  from the provided byte array and runs until the
     *                  provided byteArray is empty.
     */
    private void runExtractionLoop(ByteArray byteArray){

        while(byteArray.size() > 0){
            byteArray = this.extractNextSection(byteArray);
        }
    }

    /**
     * @return Returns the byte id for this tag.
     */
    @Override
    public TagType getTypeId() {
        return TagType.COMPOUND;
    }

    /**
     * @return Returns null on compound tags.
     */
    @Override
    public Object getValue() {
        return null;
    }

    /**
     * @param value Does nothing on compound tags.
     */
    @Override
    public void setValue(Object value) {

    }

    /**
     * @param child Children are added to the compound tag, children should have
     *              direct relationship to the parent for example a
     *              person would be a compound, with the arms and legs
     *              being child tags belonging to the parent.
     */
    public void addChild(Tag child){

        this.children.add(child);
    }

    /**
     * @param key Accepts a key that is the unique identifier of a child tag,
     *           if two children have the same key then the last matching
     *            entry will be returned.
     * @return Returns a child tag matching the provided key, can be null.
     */
    public Tag getChild(String key){
        Tag selected = null;

        for (Tag child : this.children) {
            if (child.getKey().equals(key)) {
                selected = child;
            }
        }
        return selected;
    }

    /**
     * @return Returns the compiled byte array data ready to be sent
     * over the network or saved to a file.
     */
    @Override
    public byte[] write() {

        //Firstly we create our byte array to store our child data
        ByteArray childData = new ByteArray();

        //Next we write all the child data to the byte array.
        this.children.forEach((n)->childData.addBytes(n.write()));

        //Now we can create our standard return byte array.
        ByteArray byteArray = new ByteArray();

        //First enter the tag type, in this case compound.
        byteArray.addByte(this.type.getId());

        //Next we allocate the next two bytes to the length of the key
        byteArray.addBytes(ByteBuffer.allocate(2).putShort((short)this.key.length()).array());

        //Provided the key is not empty we then add the key.
        if(!this.key.isEmpty()){
            byteArray.addBytes(this.key.getBytes());
        }

        //Finally we then allocate 4 bytes to our size and then specify how large the size
        //of the child array is.
        byteArray.addBytes(ByteBuffer.allocate(4).putInt(childData.size()).array());

        //Lastly we can add the child array on the end and return the byte array.
        byteArray.addBytes(childData.get());

        return byteArray.get();
    }

    @Override
    public String toString(){

        String output = "";

        if(this.key.isEmpty()) {
            output += this.getClass().getSimpleName()+": ";
        }else{
            output += this.getClass().getSimpleName() + "('" + this.key + "'): ";
        }

        output+="\n    {";

        for (Tag child : this.children) {
            output += "\n    "+child;
        }

        return output += "\n    }";
    }

}
