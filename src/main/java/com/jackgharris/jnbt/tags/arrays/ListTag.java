package com.jackgharris.jnbt.tags.arrays;

import com.jackgharris.jnbt.Tag;
import com.jackgharris.jnbt.utils.TagType;
import com.jackgharris.jnbt.tags.CompoundTag;

/**
 * The list tag operates similar to a compound tag
 * except it contains only names entries of the
 * same type, for example a list of integer tags
 * can only contain integers.
 */
public class ListTag extends CompoundTag {

    private TagType tagType;

    /**
     * @param key The name of this list as a string
     * @param type The tag type for this list.
     */
    public ListTag(String key, TagType type) {
        super(key);
        this.tagType = type;
        this.type = TagType.LIST;
    }


    /**
     * @param type the tag type for this list.
     */
    public ListTag(TagType type){
        super("");
        this.tagType = type;
        this.type = TagType.LIST;
    }

    /**
     * @param data Accepting data as an array of bytes this
     *      constructor will rebuild the compound tag
     *      object and children.
     */
    public ListTag(byte[] data){
        super(data);
    }

    /**
     * @param child Children added to the list must be of the
     *              same type as the list type provided in the
     *              constructor. Children of lists are nameless
     *              and will have their names removed if present
     *              prior to writing.
     */
    public void addChild(Tag child){
        if(child.getTypeId() != this.tagType){
            throw new RuntimeException("Invalid tag type provided, list accepts "+this.tagType.getId()+" only.");
        }
       child.removeKey();
       this.children.add(child);
    }

    /**
     * @return Returns the byte id for this tag.
     */
    @Override
    public TagType getTypeId() {
        return TagType.LIST;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue(Object value) {
        //Do nothing
    }
}
