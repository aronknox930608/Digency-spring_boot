package ma.digency.gov.amc.utils;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * cette classe contient les méthodes fondamentales afin d'effectuer un service de PATCH
 */
public class PatchUtils {

    /**
     * private constructor.
     */
    private PatchUtils() {
        super();
    }


    public static <T> List<T> copyList(final List<T> originalList) {
        return originalList.stream().collect(Collectors.toList());
    }

    /**
     * Service permet de recuperer la liste des objets à supprimer
     *
     * @param originalList liste recuperé depuis la base de données
     * @param patchedList  liste recuperé depuis le requestDto
     * @return <T>     liste à retourner
     */
    public static <T> List<T> getObjectToBeDeleted(final List<T> originalList, final List<T> patchedList) {
        if (originalList != null) {
            List<T> listToBeDeleted = copyList(originalList);
            listToBeDeleted.removeAll(patchedList);
            return listToBeDeleted;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Service permet de recuperer la liste des objets à ajouter
     *
     * @param originalList liste recuperé depuis la base de données
     * @param patchedList  liste recuperé depuis le requestDto
     * @return <T>     liste à retourner
     */
    public static <T> List<T> getObjectToBeAdd(final List<T> originalList, final List<T> patchedList) {
        if (patchedList != null) {
            List<T> listToBeAdd = copyList(patchedList);
            listToBeAdd.removeAll(originalList);
            return listToBeAdd;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Service permet de recuperer la liste des objets à modifier
     *
     * @param originalList liste recuperé depuis la base de données
     * @param patchedList  liste recuperé depuis le requestDto
     * @return <T>     liste à retourner
     */
    public static <T> List<T> getObjectToBeUpdated(final List<T> originalList, final List<T> patchedList) {
        if (originalList != null && patchedList != null) {
            List<T> listToBeUpdated = new LinkedList<>();
            patchedList.forEach(element -> {
                if (originalList.contains(element)) {
                    listToBeUpdated.add(element);
                }
            });
            return listToBeUpdated;
        } else {
            return Collections.emptyList();
        }

    }

    /**
     * pour verifier si une valeur a changé apres un patch appliqué
     *
     * @param newValue
     * @param oldValue
     * @return true si la la nouvelle valeur est different de null et different de l'ancienne valeur
     */
    public static boolean isToBeChecked(final Object newValue, final Object oldValue) {
        if (!StringUtils.isEmpty(newValue)) {
            return !newValue.equals(oldValue);
        } else {
            return false;
        }
    }
}
