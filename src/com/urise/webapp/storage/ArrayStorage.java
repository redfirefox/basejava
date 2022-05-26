package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    final int maxSize = 10000;
    final String notExist = "Resume %s not found%n";
    private int size = 0;
    private Resume[] storage = new Resume[maxSize];

    public int getSize() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.printf(notExist, resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (size == maxSize) {
            System.out.printf("Array overflow - max size:%s%n", maxSize);
            return;
        }
        if (getIndex(resume.getUuid()) != -1) {
            System.out.printf("Resume %s already exists%n", resume.getUuid());
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.printf(notExist, uuid);
            return null;
        } else return storage[i];
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i == -1) {
            System.out.printf(notExist, uuid);
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
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return i;
            }
        }
        return -1;
    }
}
