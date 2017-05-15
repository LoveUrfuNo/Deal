package springbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import springbackend.model.SearchRequest;
import springbackend.model.Service;
import springbackend.service.SearchService;
import springbackend.service.ServiceForService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link springbackend.service.SearchService} interface.
 */
@org.springframework.stereotype.Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ServiceForService serviceForService;

    @Autowired
    private SearchService searchService;

    @Override
    public Set<springbackend.model.Service> searchOccurrences(SearchRequest searchRequest) {
        TreeSet<Service> allServiceSet = new TreeSet<>(
                (o1, o2) -> o1.getNameOfService().compareToIgnoreCase(o2.getNameOfService()));

        allServiceSet.addAll(this.serviceForService.findAll());
        Set<Service> resultsSet = allServiceSet.stream()
                .filter(temp ->
                        Arrays.stream(temp.getNameOfService().split(" "))
                                .anyMatch(searchRequest.getSearchLine()::equalsIgnoreCase)             //TODO: add search by many words (not by one as now)
                                ||
                                Arrays.stream(temp.getDescription().split(" "))
                                        .anyMatch(searchRequest.getSearchLine()::equalsIgnoreCase))
                .collect(Collectors.toSet());

        return resultsSet;
    }

    @Override
    public TreeSet<String> crateDictionary() {
        TreeSet<String> result = new TreeSet<>(String::compareToIgnoreCase);

        Set<springbackend.model.Service> allServiceSet = this.serviceForService.findAll();
        allServiceSet.forEach(service -> {
            result.addAll(Arrays.asList(service.getDescription().split(" ")));
            result.addAll(Arrays.asList(service.getNameOfService().split(" ")));
            result.addAll(Arrays.asList(service.getCategory().split(" ")));
        });

        return result;
    }

    @Override
    public int getPrefixDistance(CharSequence userString, CharSequence second) {
        int result;
        int firstLength = userString.length();
        int secondLength = second.length();

        int[][] arr = new int[firstLength + 1][secondLength + 1];
        for (int i = 0; i <= firstLength; i++) {
            arr[i][0] = i;
        }
        for (int j = 0; j <= secondLength; j++) {
            arr[0][j] = j;
        }

        for (int i = 1; i < firstLength + 1; i++) {
            for (int j = 1; j < secondLength + 1; j++) {
                arr[i][j] = D(i - 1, j - 1, userString, second);
            }
        }

        result = arr[firstLength][0];
        for (int i = 0; i < secondLength; i++) {
            if (arr[firstLength][i] < result) {
                result = arr[firstLength][i];
            }
        }

        return result;
    }

    @Override
    public int D(int i, int j, CharSequence userString, CharSequence second) {
        if (i == 0 && j == 0)
            return 0;
        else if (j == 0 && i > 0)
            return i;
        else if (i == 0 && j > 0)
            return j;
        else {
            return minimum(D(i, j - 1, userString, second) + 1,
                    D(i - 1, j, userString, second) + 1,
                    D(i - 1, j - 1, userString, second) + (userString.charAt(i) == second.charAt(j) ? 0 : 1)
            );
        }
    }

    @Override
    public int minimum(int first, int second, int third) {
        if (first == second && first == third)
            return first;
        else if (first <= second && first <= third)
            return first;
        else if (second <= first && second <= third)
            return second;
        else
            return third;
    }
}
