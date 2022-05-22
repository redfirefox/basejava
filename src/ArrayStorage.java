/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    int size() {
        return size;
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (get(r.uuid) == null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                storage[i] = null;
        }

        Resume[] temp = new Resume[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                temp[j] = storage[i];
                j++;
            }
        }

        for (int i = 0; i < size; i++) {
            storage[i] = temp[i];
        }

        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r = new Resume[size];
        for (int i = 0; i < size; i++) {
            r[i] = storage[i];
        }
        return r;
    }
}
