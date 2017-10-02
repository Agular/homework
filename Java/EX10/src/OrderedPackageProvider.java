import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by raluga on 17.03.2016.
 * Get your packages, get your daily packages!
 */
public class OrderedPackageProvider implements PackageProvider {
    /**
     * List that contains the packages.
     * Vrooooom, G4S is coming.
     */
    private ArrayList<Package> packages = new ArrayList<>();
    /**
     * Filter for the package.
     * Filter that s***.
     */
    private PackageFilter filter;
    /**
     * Broken package provider.
     */
    private PackageProvider brokenPackageProvider;

    @Override
    public Package getNextPackage() {
        if (!packages.isEmpty()) {
            Package nextPackage = packages.get(0);
            return packages.remove(0);
        } else {
            return null;
        }
    }

    @Override
    public void addPackage(Package packageToAdd) {
        if (!packages.contains(packageToAdd) && packageToAdd != null) {
            packages.add(packageToAdd);
            Collections.sort(packages);
        }
    }

    @Override
    public boolean hasNextPackage() {
        return !packages.isEmpty();
    }

    @Override
    public void setPackageFilter(PackageFilter packageFilter) {
        this.filter = packageFilter;
    }

    @Override
    public PackageFilter getPackageFilter() {
        return filter;
    }

    @Override
    public void setBrokenPackageProvider(PackageProvider packageProvider) {
        this.brokenPackageProvider = packageProvider;
    }

    @Override
    public PackageProvider getBrokenPackageProvider() {
        return brokenPackageProvider;
    }

    @Override
    public List<Package> getPackages() {
        return packages;
    }

    @Override
    public List<Package> findAllPackagesBySender(Customer customer) {
        if (customer == null) {
            return null;
        }
        ArrayList<Package> senderPackages = new ArrayList<>();
        for (Package aPackage : packages) {
            if (aPackage.getSender().getName().equals(customer.getName())) {
                senderPackages.add(aPackage);
            }
        }
        return senderPackages;
    }

    @Override
    public List<Package> findAllPackagesByReceiver(Customer customer) {
        if (customer == null) {
            return null;
        }
        ArrayList<Package> receiverPackages = new ArrayList<>();
        for (Package aPackage : packages) {
            if (aPackage.getReceiver().getName().equals(customer.getName())) {
                receiverPackages.add(aPackage);
            }
        }
        return receiverPackages;
    }
}
