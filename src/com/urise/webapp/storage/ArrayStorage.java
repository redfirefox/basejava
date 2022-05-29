package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static int MAX_SIZE = 10000;
    private static String RESUME_NOT_EXIST = "Resume %s not found%n";
    private int size = 0;
    private Resume[] storage = new Resume[MAX_SIZE];

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.printf(RESUME_NOT_EXIST, resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.printf("Resume %s already exists%n", resume.getUuid());
        } else if (size == MAX_SIZE) {
            System.out.printf("Array overflow - max size:%s%n", MAX_SIZE);
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.printf(RESUME_NOT_EXIST, uuid);
            return null;
        } else {
            return storage[i];
        }
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.printf(RESUME_NOT_EXIST, uuid);
            return;
        }
        storage[i] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
