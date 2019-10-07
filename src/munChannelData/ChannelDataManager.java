package munChannelData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import numberlist.objectlist.*;
import numberlist.primitivelist.*;

import java.util.ArrayList;
import java.util.Collections;

import numberlist.InvalidIndexException;

/**
 * The videoLibraryManager class is used to maintain a data base of videos on the
 * business layer
 *
 * @author Seonjun Mun
 * @version 09/23/2019
 */
public class ChannelDataManager implements java.io.Serializable {

    private ArrayList<String> names;
    private ObjectArrayList views;
    private IntegerArrayList dates;


    /**
     * default constructor-- creates a new instance of videoLibraryManager() with
     * default parameters.
     */
    public ChannelDataManager() {
        //set defualt values
        names = new ArrayList<>();
        views = new ObjectArrayList();
        dates = new IntegerArrayList();
    }

    /**
     * deletes the video at the selected index
     *
     * @param index- the index of the video to delete
     * @throws numberlist.InvalidIndexException
     */
    public void DeleteVideo(int index) throws InvalidIndexException {
        //remove the selected video from the list
        names.remove(index);
        views.removeAt(index);
        dates.removeAt(index);
    }

    /**
     * Adds a new video with the passed in parameters
     *
     * @param name- the name of the video to add
     * @param view- the views of the video to add
     * @param date- the date of the video to add
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void AddVideo(String name, Money view, long date) throws InvalidIndexException, UncopiableException {
        names.add(name);
        views.add(views.size(), view);
        dates.add(date);

    }

    /**
     * Returns the number of items in the video library
     *
     * @return the number of items
     */
    public int getCount() {
        return names.size();
    }

    /**
     * Returns the name of the video at the passed in index
     *
     * @param index- the index to get the name from
     * @return the name of the video
     */
    public String GetVideoName(int index) {
        return names.get(index);
    }

    /**
     * Returns the views of the video at the passed in index
     *
     * @param index- the index of the video
     * @return the views of the video
     */
    public Money GetVideoViews(int index) {
        Money view = (Money) views.get(index);
        return (Money) views.get(index);
    }

    /**
     * Returns the date of the video at the passed in index
     *
     * @param date- the date of the passed in index
     * @return the date
     * @throws numberlist.InvalidIndexException
     */
    public Long GetVideoDate(int date) throws InvalidIndexException {
        return dates.get(date);
    }

    /**
     * Sorts the videos by date
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void SortByDate() throws InvalidIndexException, UncopiableException {
        //make the indices sorted array
        int[] indicesSorted = new int[dates.size()];
        IntegerArrayList copy = new IntegerArrayList();
        ArrayList<String> strings = new ArrayList();
        ObjectArrayList viewsCopy = (ObjectArrayList) views.deepCopy();
        for (int i = 0; i < dates.size(); i++) {
            indicesSorted[i] = i;
        }
        //mersort the array
        SortByDateMergeSort(indicesSorted, 0, indicesSorted.length - 1);
        for (int i = 0; i < dates.size(); i++) {
            copy.add(dates.get(i));
            strings.add(names.get(i));
        }

        for (int i = 0; i < dates.size(); i++) {
            dates.set(i, copy.get(indicesSorted[i]));
            names.set(i, strings.get(indicesSorted[i]));
            views.set(i, viewsCopy.get(indicesSorted[i]));

        }
    }

    /**
     * Sorts the videos by date using merge sort
     *
     * @param unsortedArray- the unsorted array to sort
     * @param left           the left index
     * @param right          the right index
     */
    private void SortByDateMergeSort(int[] unsortedArray, int left, int right) throws InvalidIndexException {
        int middle;
        if (left < right) {
            middle = (left + right) / 2;
            SortByDateMergeSort(unsortedArray, left, middle);
            SortByDateMergeSort(unsortedArray, middle + 1, right);
            mergeByDate(unsortedArray, left, middle, right);
        }
    }

    /**
     * merges the array by date
     *
     * @param unsortedArray the unsorted array
     * @param left          the left index
     * @param middle        the middle index
     * @param right         the right index
     */
    private void mergeByDate(int[] unsortedArray, int left, int middle, int right) throws InvalidIndexException {
        int[] temp = new int[unsortedArray.length];
        int r = right;
        int l = left;
        int m = middle + 1;
        int k = l;
        while (l <= middle && m <= r) {
            if (dates.get(unsortedArray[l]) >= dates.get(unsortedArray[m])) {
                temp[k++] = unsortedArray[l++];
            } else {
                temp[k++] = unsortedArray[m++];
            }
        }
        while (l <= middle) {
            temp[k++] = unsortedArray[l++];
        }

        while (m <= r) {
            temp[k++] = unsortedArray[m++];
        }

        for (int i = left; i <= right; i++) {
            unsortedArray[i] = temp[i];
        }
    }

    /**
     * Sorts the videos by views using Mergesort.
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void SortByViews() throws InvalidIndexException, UncopiableException {

        int[] indicesSorted = new int[views.size()];
        IntegerArrayList copy = new IntegerArrayList();
        ArrayList<String> strings = new ArrayList();
        ObjectArrayList viewsCopy = (ObjectArrayList) views.deepCopy();
        for (int i = 0; i < views.size(); i++) {
            indicesSorted[i] = i;
        }
        //mergesort the array
        SortByViewsMergeSort(indicesSorted, 0, indicesSorted.length - 1);
        for (int i = 0; i < views.size(); i++) {
            copy.add(dates.get(i));
            strings.add(names.get(i));
        }

        for (int i = 0; i < views.size(); i++) {
            dates.set(i, copy.get(indicesSorted[i]));
            names.set(i, strings.get(indicesSorted[i]));
            views.set(i, viewsCopy.get(indicesSorted[i]));
        }

    }

    /**
     * Sorts the video by views using merge sort
     *
     * @param unsortedArray- the unsorted array to sort
     * @param left           the left index
     * @param right          the right index
     */
    private void SortByViewsMergeSort(int[] unsortedArray, int left, int right) {
        int middle;
        if (left < right) {
            middle = (left + right) / 2;
            SortByViewsMergeSort(unsortedArray, left, middle);
            SortByViewsMergeSort(unsortedArray, middle + 1, right);
            mergeByViews(unsortedArray, left, middle, right);
        }
    }

    /**
     * merges the array by views
     *
     * @param unsortedArray the unsorted array
     * @param left          the left index
     * @param middle        the middle index
     * @param right         the right index
     */
    private void mergeByViews(int[] unsortedArray, int left, int middle, int right) {
        int[] temp = new int[unsortedArray.length];
        int r = right;
        int l = left;
        int m = middle + 1;
        int k = l;
        while (l <= middle && m <= r) {
            if (((Money) views.get(unsortedArray[l])).compareTo((Money) views.get(unsortedArray[m])) > 1) {
                temp[k++] = unsortedArray[l++];
            } else {
                temp[k++] = unsortedArray[m++];
            }
        }
        while (l <= middle) {
            temp[k++] = unsortedArray[l++];
        }

        while (m <= r) {
            temp[k++] = unsortedArray[m++];
        }

        for (int i = left; i <= right; i++) {
            unsortedArray[i] = temp[i];
        }
    }

    /**
     * Sorts the videos by name using Mergesort
     *
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    public void SortByName() throws InvalidIndexException, UncopiableException {

        int[] indicesSorted = new int[names.size()];
        IntegerArrayList copy = new IntegerArrayList();
        ArrayList<String> strings = new ArrayList();
        ObjectArrayList viewsCopy = (ObjectArrayList) views.deepCopy();
        for (int i = 0; i < names.size(); i++) {
            indicesSorted[i] = i;
        }
        //mersort the array
        SortByNameMergeSort(indicesSorted, 0, indicesSorted.length - 1);
        for (int i = 0; i < names.size(); i++) {
            copy.add(dates.get(i));
            strings.add(names.get(i));
        }

        for (int i = 0; i < names.size(); i++) {
            dates.set(i, copy.get(indicesSorted[i]));
            names.set(i, strings.get(indicesSorted[i]));
            views.set(i, viewsCopy.get(indicesSorted[i]));
        }

    }

    /**
     * Sorts the videos by name using merge sort
     *
     * @param unsortedArray- the unsorted array to sort
     * @param left           the left index
     * @param right          the right index
     */
    private void SortByNameMergeSort(int[] unsortedArray, int left, int right) {
        int middle;
        if (left < right) {
            middle = (left + right) / 2;
            SortByNameMergeSort(unsortedArray, left, middle);
            SortByNameMergeSort(unsortedArray, middle + 1, right);
            mergeByName(unsortedArray, left, middle, right);
        }
    }

    /**
     * merges the array by name
     *
     * @param unsortedArray the unsorted array
     * @param left          the left index
     * @param middle        the middle index
     * @param right         the right index
     */
    private void mergeByName(int[] unsortedArray, int left, int middle, int right) {
        int[] temp = new int[unsortedArray.length];
        int r = right;
        int l = left;
        int m = middle + 1;
        int k = l;
        while (l <= middle && m <= r) {
            if (names.get(unsortedArray[l]).compareToIgnoreCase(names.get(unsortedArray[m])) < 1) {
                temp[k++] = unsortedArray[l++];
            } else {
                temp[k++] = unsortedArray[m++];
            }
        }
        while (l <= middle) {
            temp[k++] = unsortedArray[l++];
        }

        while (m <= r) {
            temp[k++] = unsortedArray[m++];
        }

        for (int i = left; i <= right; i++) {
            unsortedArray[i] = temp[i];
        }
    }

    /**
     *
     */
    public void Serialize() {
        //write this class out to a file
        try {
            try (FileOutputStream fOut = new FileOutputStream("librayData.ld");
                 ObjectOutputStream oOut = new ObjectOutputStream(fOut)) {
                oOut.writeObject(this);
            }

        } catch (IOException ex) {
        }
    }

    /**
     * Loads a videoLibrary from memory
     *
     * @return the video library manager that was loaded
     */
    public static ChannelDataManager Deserialize() {
        ChannelDataManager library = null;
        try {

            FileInputStream fIn = new FileInputStream("librayData.ld");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            library = (ChannelDataManager) oIn.readObject();
            oIn.close();
            fIn.close();
        } catch (Exception ex) {

        }
        return library;
    }

}
