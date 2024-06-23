
import random

# Define the VM and Host classes
class VM:
    '''
    VM: vm_id, mips, ram
    '''
    def __init__(self, vm_id, mips, ram):
        self.vm_id = vm_id
        self.mips = mips
        self.ram = ram

class Host:
    '''
    Host: host_id, mips_capacity, ram_capacity, vm_list
    '''
    def __init__(self, host_id, mips_capacity, ram_capacity):
        self.host_id = host_id
        self.mips_capacity = mips_capacity
        self.ram_capacity = ram_capacity
        self.vm_list = []

    def can_allocate(self, vm):
        return (self.mips_capacity >= vm.mips and
                self.ram_capacity >= vm.ram)

    def allocate_vm(self, vm):
        # allocate if capacity requirements are fulfilled.
        if self.can_allocate(vm):
            self.vm_list.append(vm)
            self.mips_capacity -= vm.mips
            self.ram_capacity -= vm.ram
            return True
        return False

class GeneticAlgorithm:
    '''
    GA: population_size, crossover_rate, mutation_rate, max_gen, vms, hosts, population
    '''
    def __init__(self, population_size, crossover_rate, mutation_rate, max_generations, vms, hosts):
        self.population_size = population_size
        self.crossover_rate = crossover_rate
        self.mutation_rate = mutation_rate
        self.max_generations = max_generations
        self.vms = vms
        self.hosts = hosts
        self.population = []

    def initialize_population(self):
        for _ in range(self.population_size):
            allocation = self.generate_random_allocation()
            self.population.append(allocation)

    def generate_random_allocation(self):
        return [random.choice(self.hosts) for _ in range(len(self.vms))]

    def calculate_fitness(self, allocation):
        total_fitness = 0.0
        for host in self.hosts:
            host_mips_utilization = sum(vm.mips for vm, allocated_host in zip(self.vms, allocation) if allocated_host == host)
            total_fitness += host_mips_utilization / host.mips_capacity
        return total_fitness

    def selection(self):
        sorted_population = sorted(self.population, key=lambda x: self.calculate_fitness(x), reverse=True)
        return sorted_population[:self.population_size // 2]

    def crossover(self, parent1, parent2):
        crossover_point = random.randint(1, len(parent1) - 2)
        child1 = parent1[:crossover_point] + parent2[crossover_point:]
        child2 = parent2[:crossover_point] + parent1[crossover_point:]
        return child1, child2

    def mutate(self, child):
        mutation_point = random.randint(0, len(child) - 1)
        child[mutation_point] = random.choice(self.hosts)

    def evolve(self):
        self.initialize_population()
        for generation in range(self.max_generations):
            parents = self.selection()
            offspring = []
            for i in range(0, len(parents), 2):
                parent1 = parents[i]
                parent2 = parents[i + 1]
                if random.random() < self.crossover_rate:
                    child1, child2 = self.crossover(parent1, parent2)
                    offspring.append(child1)
                    offspring.append(child2)
                else:
                    offspring.append(parent1)
                    offspring.append(parent2)
            for child in offspring:
                if random.random() < self.mutation_rate:
                    self.mutate(child)
            self.population = parents + offspring

    def get_best_solution(self):
        best_allocation = max(self.population, key=lambda x: self.calculate_fitness(x))
        return best_allocation

# Example usage with simplified parameters
if __name__ == '__main__':
    # Define VMs and Hosts with simplified parameters
    vms = [VM(vm_id, random.randint(500, 1500), random.randint(256, 1024)) for vm_id in range(20)]
    hosts = [Host(host_id, 5000, 8192) for host_id in range(3)]

    # Genetic Algorithm parameters
    population_size = 100
    crossover_rate = 0.8
    mutation_rate = 0.1
    max_generations = 50

    # Initialize and run Genetic Algorithm
    ga = GeneticAlgorithm(population_size, crossover_rate, mutation_rate, max_generations, vms, hosts)
    ga.evolve()

    # Retrieve best solution (best VM allocation)
    best_allocation = ga.get_best_solution()

    allocation_count = [0, 0, 0]

    num = 0
    for host in best_allocation:
        print(f"Host ID: {host.host_id}, MIPS Capacity: {host.mips_capacity}, RAM Capacity: {host.ram_capacity}")

        allocation_count[host.host_id] += 1
        num += 1
    print(num)
    print(allocation_count)


    [5, 7, 8]
    #0, 2, 4, 6, 8, 10, 12, 14, 16, 18 = allocate 8 to [2], 2 to [1]
    #1, 3, 5, 7, 9, 11, 13, 15, 17, 19 = allocate 5 to [1], 5 to [0]








